package com.sid.thermostat.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.sid.thermostat.app.dto.DeviceDto;
import com.sid.thermostat.app.exceptions.RestException;
import com.sid.thermostat.app.inbound.message.observers.DeviceDataObserver;
import com.sid.thermostat.app.mongo.entites.Device;
import com.sid.thermostat.app.mongo.repositories.DeviceRepository;
import com.sid.thermostat.app.protobuf.ProvisioningRequest;
import com.sid.thermostat.app.task.executor.ProvisioningTask;
import com.sid.thermostat.app.task.executor.TaskManager;
import com.sid.thermostat.app.util.EntityAndDtoConversionUtil;

@Component
public class DeviceServiceImpl implements DeviceService {

	private static Logger logger = Logger.getLogger(DeviceDataObserver.class.getName());

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private TaskManager taskManager;

	@Autowired
	private ProvisioningTask provisioningTask;

	@Override
	public void provisionDevice(ProvisioningRequest provisioningRequest) {
		Optional<Device> optionalDevice = deviceRepository.findBySerialNo(provisioningRequest.getSerialNo());
		if (optionalDevice.isPresent()) {
			logger.log(Level.WARNING,
					"Device is already provisoned, serialNo[" + optionalDevice.get().getSerialNo() + "]");
		} else {
			logger.log(Level.INFO, "Provisioning device, serialNo[" + provisioningRequest.getSerialNo() + "]");
			provisioningTask.setRequest(provisioningRequest);
			taskManager.execute(provisioningTask);
		}
	}

	@Override
	public List<DeviceDto> getAllDevices() throws RestException {
		List<Device> devices = deviceRepository.findAll();
		List<DeviceDto> devicesResponse = new ArrayList<>();
		if (devices.isEmpty()) {
			throw new RestException(HttpStatus.NO_CONTENT, "No Devices available.");
		} else {
			devices.forEach(device -> devicesResponse.add(EntityAndDtoConversionUtil.convert(device, DeviceDto.class)));
		}
		return devicesResponse;
	}

	@Override
	public DeviceDto getDeviceBySerialNo(String serialNo) throws RestException {
		Optional<Device> device = deviceRepository.findBySerialNo(serialNo);
		DeviceDto deviceDto = new DeviceDto();
		if (device.isPresent()) {
			deviceDto = EntityAndDtoConversionUtil.convert(device.get(), DeviceDto.class);
		} else {
			throw new RestException(HttpStatus.NOT_FOUND, "Device not found.");
		}
		return deviceDto;
	}
}
