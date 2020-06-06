package com.sid.thermostat.app.service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sid.thermostat.app.inbound.message.observers.DeviceDataObserver;
import com.sid.thermostat.app.mongo.entites.Device;
import com.sid.thermostat.app.mongo.repositories.ConfigurationRepository;
import com.sid.thermostat.app.mongo.repositories.DeviceRepository;
import com.sid.thermostat.app.outbound.message.template.OutboundMessageTemplate;
import com.sid.thermostat.app.protobuf.ProvisioningRequest;
import com.sid.thermostat.app.task.executor.ProvisioningTask;
import com.sid.thermostat.app.task.executor.TaskManager;

@Component
public class DeviceServiceImpl implements DeviceService {

	private static Logger logger = Logger.getLogger(DeviceDataObserver.class.getName());

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private ConfigurationRepository configRepository;

	@Autowired
	private TaskManager taskManager;

	@Autowired
	private OutboundMessageTemplate outboundMessagePublisher;

	@Autowired
	private ProvisioningTask provisioningTask;

	@Override
	public void provisionDevice(ProvisioningRequest provisioningRequest) {
		Optional<Device> optionalDevice = deviceRepository.findBySerialNo(provisioningRequest.getSerialNo());
		if (optionalDevice.isPresent()) {
			logger.log(Level.WARNING, "Device is already provisoned, serialNo[" + optionalDevice.get() + "]");
		} else {
			logger.log(Level.INFO, "Provisioning device, serialNo[" + provisioningRequest.getSerialNo() + "]");
			provisioningTask.setRequest(provisioningRequest);
			taskManager.execute(provisioningTask);
		}
	}
}
