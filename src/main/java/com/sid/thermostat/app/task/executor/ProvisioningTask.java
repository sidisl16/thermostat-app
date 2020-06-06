package com.sid.thermostat.app.task.executor;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sid.thermostat.app.mongo.entites.Config;
import com.sid.thermostat.app.mongo.entites.Device;
import com.sid.thermostat.app.mongo.entites.Status;
import com.sid.thermostat.app.mongo.repositories.ConfigurationRepository;
import com.sid.thermostat.app.mongo.repositories.DeviceRepository;
import com.sid.thermostat.app.outbound.message.template.OutboundMessageTemplate;
import com.sid.thermostat.app.outbound.message.template.PublishRequest;
import com.sid.thermostat.app.protobuf.ProvisioningRequest;
import com.sid.thermostat.app.protobuf.ProvisioningResponse;
import com.sid.thermostat.app.protobuf.ProvisioningResponse.ProvStatus;

@Component
@Scope(value = "prototype")
public class ProvisioningTask extends AsyncTask {

	private static Logger logger = Logger.getLogger(ProvisioningTask.class.getName());

	private static final String DEFAULT_DATA_TOPIC_PATTERN = "/inbound/data/%s";
	private static final String DEFAULT_CONFIG_TOPIC_PATTERN = "/inbound/configuration/%s";
	private static final String DEFAULT_PROVISIONING_TOPIC_PATTERN = "/outbound/provisioning/%s";

	private ProvisioningRequest request;

	private DeviceRepository deviceRepository;

	private ConfigurationRepository configRepository;

	private OutboundMessageTemplate outboundMessagePublisher;

	public ProvisioningRequest getRequest() {
		return request;
	}

	public void setRequest(ProvisioningRequest request) {
		this.request = request;
	}

	@Autowired
	public ProvisioningTask(DeviceRepository deviceRepository, ConfigurationRepository configRepository,
			OutboundMessageTemplate outboundMessagePublisher) {
		this.deviceRepository = deviceRepository;
		this.configRepository = configRepository;
		this.outboundMessagePublisher = outboundMessagePublisher;
	}

	@Override
	public void defineAsyncTask() {
		logger.log(Level.FINE, "Started excetuing provisioning thread for serial no +[" + request.getSerialNo() + "]");
		logger.log(Level.INFO, "persisting device, serial no +[" + request.getSerialNo() + "]");
		Device device = persistDevice();
		logger.log(Level.INFO, "Persisting default config for device, serial no +[" + request.getSerialNo() + "]");
		Config defaultConfig = persistDefaultConfig(device);
		device.setConfig(defaultConfig);
		device = deviceRepository.save(device);
		logger.log(Level.INFO, "Sending provisioning response for device, serial no +[" + request.getSerialNo() + "]");
		sendOutboundMessage(device, defaultConfig);
	}

	private void sendOutboundMessage(Device device, Config config) {
		ProvisioningResponse response = getProvisioningResponseObject(device, config);
		PublishRequest request = PublishRequest.newBuilder().setPayload(response.toByteArray()).setQos(1)
				.setRetain(true).setTopic(String.format(DEFAULT_PROVISIONING_TOPIC_PATTERN, device.getSerialNo()))
				.build();
		getOutboundPublisher().publish(request);
		logger.log(Level.INFO,
				"Provisioning response for device, serial no +[" + device.getSerialNo() + "] published successfully.");
	}

	private ProvisioningResponse getProvisioningResponseObject(Device device, Config config) {
		return ProvisioningResponse.newBuilder().setConfigTopic(config.getConfigTopic())
				.setDataTopic(config.getConfigTopic()).setQos(1).setSerialNo(device.getSerialNo())
				.setStatus(ProvStatus.SUCCESS).build();
	}

	private Config persistDefaultConfig(Device device) {
		// Persist device default config
		long now = System.currentTimeMillis();
		Config config = new Config();
		config.setConfigTopic(String.format(DEFAULT_CONFIG_TOPIC_PATTERN, device.getSerialNo()));
		config.setCreatedAt(now);
		config.setDataInterval(5000);
		config.setDataTopic(String.format(DEFAULT_DATA_TOPIC_PATTERN, device.getSerialNo()));
		config.setLastModified(now);
		config.setRetryAttempt(5);
		// Initial Config would go as a part of provisioning response, so it is always
		// completed
		config.setStatus(Status.COMPLETED);
		config = configRepository.save(config);
		return config;
	}

	private Device persistDevice() {
		// Persist device
		Device device = new Device();
		device.setIpAddress(request.getSerialNo());
		device.setMacAddress(request.getMacAddress());
		device.setSerialNo(request.getSerialNo());
		return deviceRepository.save(device);
	}

	private OutboundMessageTemplate getOutboundPublisher() {
		return this.outboundMessagePublisher;
	}
}
