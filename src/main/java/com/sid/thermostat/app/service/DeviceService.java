package com.sid.thermostat.app.service;

import java.util.List;

import com.sid.thermostat.app.dto.DeviceDto;
import com.sid.thermostat.app.exceptions.RestException;
import com.sid.thermostat.app.protobuf.ProvisioningRequest;

public interface DeviceService {
	public void provisionDevice(ProvisioningRequest provisioningRequest);

	List<DeviceDto> getAllDevices() throws RestException;

	public DeviceDto getDeviceBySerialNo(String serialNo) throws RestException;
}
