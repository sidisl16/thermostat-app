package com.sid.thermostat.app.task.executor;

import java.util.Optional;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.protobuf.GeneratedMessageV3;
import com.sid.thermostat.app.mongo.dal.ConfigurationDAL;
import com.sid.thermostat.app.mongo.entites.Config;
import com.sid.thermostat.app.mongo.entites.Status;
import com.sid.thermostat.app.mongo.repositories.ConfigurationRepository;
import com.sid.thermostat.app.mongo.repositories.DeviceRepository;
import com.sid.thermostat.app.outbound.message.template.OutboundMessageTemplate;
import com.sid.thermostat.app.outbound.message.template.PublishRequest;
import com.sid.thermostat.app.protobuf.ConfigurationRequest;
import com.sid.thermostat.app.protobuf.ConfigurationResponse;

@Component
@Scope(value = "prototype")
public class ConfigurationTask extends SyncTask<ConfigurationResponse> {

	private static Logger logger = Logger.getLogger(ConfigurationTask.class.getName());
	private static final Integer DEFAULT_MAX_RETRY = 5;
	private static final Integer DEFAULT_RESPONSE_TIMEOUT_SECS = 60;

	private ConfigurationRequest configurationRequest;
	private ConfigurationRepository configRepository;
	private OutboundMessageTemplate outboundMessagePublisher;
	private ConfigurationDAL configurationDAL;

	@Autowired
	public ConfigurationTask(DeviceRepository deviceRepository, ConfigurationRepository configRepository,
			OutboundMessageTemplate outboundMessagePublisher, ConfigurationDAL configurationDAL) {
		this.configRepository = configRepository;
		this.outboundMessagePublisher = outboundMessagePublisher;
		this.configurationDAL = configurationDAL;
	}

	public void setConfigurationRequest(ConfigurationRequest configurationRequest) {
		this.configurationRequest = configurationRequest;
	}

	@Override
	public ConfigurationResponse defineTask() throws InterruptedException {
		logger.log(Level.INFO, "Starting configuration, serial no [" + configurationRequest.getSerialNo() + "]");
		Optional<Config> optionalConfig = configurationDAL.getConfigBySerialNo(configurationRequest.getSerialNo());
		Config config = updateConfig(optionalConfig.get(), Status.INPROGRESS);
		logger.log(Level.INFO,
				"Publishing configuration message, serial no [" + configurationRequest.getSerialNo() + "]");
		ConfigurationResponse response = null;
		int retryAttempt = 1;
		do {
			try {
				getRequestCache().put(configurationRequest.getRequestId(), new SynchronousQueue<GeneratedMessageV3>());
				getOutboundMessageTemplate().publish(getPublishRequest(configurationRequest, config));
				response = (ConfigurationResponse) getRequestCache().get(configurationRequest.getRequestId())
						.poll(DEFAULT_RESPONSE_TIMEOUT_SECS, TimeUnit.SECONDS);
				break;
			} catch (InterruptedException e) {
				retryAttempt++;
				config.getNewConfig().setRetryAttempt(retryAttempt);
				configRepository.save(config);
				if (retryAttempt == DEFAULT_MAX_RETRY) {
					logger.log(Level.INFO, "configuration reached maximum retry count, serial no ["
							+ configurationRequest.getSerialNo() + "]");
					logger.log(Level.FINE, "configuration reached maximum retry count, removing request id ["
							+ configurationRequest.getRequestId() + "] from request cache.");
					getRequestCache().remove(configurationRequest.getRequestId());
					throw e;
				}
			}
		} while (retryAttempt <= DEFAULT_MAX_RETRY);
		return response;
	}

	private PublishRequest getPublishRequest(ConfigurationRequest configRequest, Config config) {
		return PublishRequest.newBuilder().setPayload(configRequest.toByteArray()).setTopic(config.getConfigTopic())
				.setQos(1).setRetain(true).build();
	}

	private OutboundMessageTemplate getOutboundMessageTemplate() {
		return outboundMessagePublisher;
	}

	private Config updateConfig(Config config, Status status) {
		config.getNewConfig().setStatus(status);
		config.getNewConfig().setLastModified(System.currentTimeMillis());
		logger.log(Level.FINE,
				"Marking configuration inprogress, serial no [" + configurationRequest.getSerialNo() + "]");
		return (config = configRepository.save(config));
	}

}
