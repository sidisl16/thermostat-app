package com.sid.thermostat.app.service;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.sid.thermostat.app.dto.ConfigDto;
import com.sid.thermostat.app.exceptions.RestException;
import com.sid.thermostat.app.mongo.dal.ConfigurationDAL;
import com.sid.thermostat.app.mongo.entites.Config;
import com.sid.thermostat.app.mongo.entites.Device;
import com.sid.thermostat.app.mongo.entites.Status;
import com.sid.thermostat.app.mongo.repositories.ConfigurationRepository;
import com.sid.thermostat.app.mongo.repositories.DeviceRepository;
import com.sid.thermostat.app.protobuf.ConfigurationRequest;
import com.sid.thermostat.app.protobuf.ConfigurationResponse;
import com.sid.thermostat.app.protobuf.ConfigurationResponse.ConfigStatus;
import com.sid.thermostat.app.task.executor.ConfigurationTask;
import com.sid.thermostat.app.task.executor.RequestCache;
import com.sid.thermostat.app.task.executor.ResponseCallback;
import com.sid.thermostat.app.task.executor.TaskManager;
import com.sid.thermostat.app.util.EntityAndDtoConversionUtil;

@Component
public class ConfigurationServiceImpl implements ConfigurationService {

	private static Logger logger = Logger.getLogger(ConfigurationServiceImpl.class.getName());

	@Autowired
	private ConfigurationDAL configurationDAL;

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private TaskManager taskManager;

	@Autowired
	private ConfigurationRepository configurationRepository;

	@Autowired
	private ConfigurationTask configurationTask;

	@Override
	public ConfigDto getConfigBySerialNo(String serialNo) throws RestException {
		Optional<Config> config = configurationDAL.getConfigBySerialNo(serialNo);
		ConfigDto configDto = new ConfigDto();
		if (config.isPresent()) {
			if (config.get().getNewConfig() != null && (config.get().getNewConfig().getStatus() != Status.COMPLETED
					|| config.get().getNewConfig().getStatus() != Status.FAILED)) {
				configDto = EntityAndDtoConversionUtil.convert(config.get().getNewConfig(), ConfigDto.class);
			} else {
				configDto = EntityAndDtoConversionUtil.convert(config.get(), ConfigDto.class);
			}
		} else {
			throw new RestException(HttpStatus.NOT_FOUND, "config not found.");
		}
		return configDto;
	}

	@Override
	public ConfigDto pushConfigForSerialNo(ConfigDto configDto, String serialNo) throws RestException {
		logger.log(Level.INFO, "Configuring device serialNo [" + serialNo + "]");
		Optional<Device> device = deviceRepository.findBySerialNo(serialNo);
		Optional<Config> optionalConfig = configurationDAL.getConfigBySerialNo(serialNo);
		validate(serialNo, device, optionalConfig);
		Config config = updateConfig(optionalConfig.get(), configDto);
		logger.log(Level.INFO, "Executing configuration for device, serialNo [" + serialNo + "]");
		ConfigurationRequest configRequest = getConfigurationRequest(config, serialNo);
		configurationTask.setConfigurationRequest(configRequest);
		optionalConfig = configurationDAL.getConfigBySerialNo(serialNo);
		excecuteConfiguration(serialNo);
		return EntityAndDtoConversionUtil.convert(optionalConfig.get().getNewConfig(), ConfigDto.class);
	}

	private void validate(String serialNo, Optional<Device> device, Optional<Config> optionalConfig)
			throws RestException {
		if (device.isPresent()) {
			if (optionalConfig.isPresent() && !(optionalConfig.get().getStatus() == Status.COMPLETED)) {
				logger.log(Level.WARNING,
						"Configuration is already in progress for device, serialNo [" + serialNo + "]");
				throw new RestException(HttpStatus.PROCESSING, "Existing config is in progress for this device.");
			}
		} else {
			logger.log(Level.SEVERE, "Could not find device, serialNo [" + serialNo + "]");
			throw new RestException(HttpStatus.NOT_FOUND, "Device not found");
		}
	}

	public Config updateConfig(Config config, ConfigDto configDto) {
		Config newConfig = config.getNewConfig();
		long now = System.currentTimeMillis();
		if (newConfig == null)
			newConfig = new Config();
		EntityAndDtoConversionUtil.copy(configDto, newConfig);
		newConfig.setStatus(Status.PENDING);
		newConfig.setCreatedAt(now);
		newConfig.setLastModified(now);
		newConfig.setRetryAttempt(1);
		config.setNewConfig(newConfig);
		return configurationRepository.save(config);
	}

	private ConfigurationRequest getConfigurationRequest(Config config, String serialNo) {
		Config newConfig = config.getNewConfig();
		ConfigurationRequest.Builder builder = ConfigurationRequest.newBuilder();
		builder.setSerialNo(serialNo);
		if (newConfig.getInConfigTopic() != null)
			builder.setInConfigTopic(newConfig.getInConfigTopic());
		if (newConfig.getOutConfigTopic() != null)
			builder.setOutConfigTopic(newConfig.getOutConfigTopic());
		if (newConfig.getDataInterval() != null)
			builder.setDataInterval(newConfig.getDataInterval());
		if (newConfig.getInDataTopic() != null)
			builder.setInDataTopic(newConfig.getInDataTopic());
		builder.setRequestId(UUID.randomUUID().toString());
		return builder.build();
	}

	private void excecuteConfiguration(String serialNo) {
		getTaskManager().execute(configurationTask, new ConfigurationTaskCallback(serialNo));
	}

	public TaskManager getTaskManager() {
		return taskManager;
	}

	private class ConfigurationTaskCallback implements ResponseCallback<ConfigurationResponse> {

		private String serialNo;

		public ConfigurationTaskCallback(String serialNo) {
			this.serialNo = serialNo;
		}

		@Override
		public void onSucess(ConfigurationResponse object) {
			Optional<Config> optionalConfig = configurationDAL.getConfigBySerialNo(serialNo);
			Config config = optionalConfig.get();
			if (object.getStatus() == ConfigStatus.SUCCESS) {
				handleSuccess(config);
			} else {
				handleFailure(config);
			}
		}

		@Override
		public void onFailure(Throwable throwable) {
			Optional<Config> optionalConfig = configurationDAL.getConfigBySerialNo(serialNo);
			handleFailure(optionalConfig.get());
		}

		private void handleSuccess(Config config) {
			config.getNewConfig().setStatus(Status.COMPLETED);
			config.getNewConfig().setLastModified(System.currentTimeMillis());
			EntityAndDtoConversionUtil.copyIgnoreNull(config.getNewConfig(), config);
			configurationRepository.save(config);
		}

		private void handleFailure(Config config) {
			config.getNewConfig().setStatus(Status.FAILED);
			config.getNewConfig().setLastModified(System.currentTimeMillis());
			configurationRepository.save(config);
		}
	}

	@Override
	public void processConfigurationResponse(ConfigurationResponse configResponse, RequestCache requestCache) {
		logger.log(Level.INFO, "Processing configuration response for serial no [" + configResponse.getSerialNo()
				+ "], request id[" + configResponse.getRequestId() + "]");
		if (requestCache.containsKey(configResponse.getRequestId())) {
			try {
				requestCache.get(configResponse.getRequestId()).put(configResponse);
				requestCache.remove(configResponse.getRequestId());
			} catch (InterruptedException e) {
				logger.log(Level.FINE,
						"Removing request id[" + configResponse.getRequestId() + "] from request cache.");
				requestCache.remove(configResponse.getRequestId());
				e.printStackTrace();
			}
		} else {
			logger.log(Level.WARNING, "Discarding message, request  configuration response for serial no ["
					+ configResponse.getSerialNo() + "], request id[" + configResponse.getRequestId() + "]");
		}
	}
}