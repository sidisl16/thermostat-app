package com.sid.thermostat.app.service;

import com.sid.thermostat.app.protobuf.ProvisioningRequest;

public interface DeviceService {
	public void provisionDevice(ProvisioningRequest provisioningRequest);
}
