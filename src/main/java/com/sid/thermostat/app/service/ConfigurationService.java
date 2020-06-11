package com.sid.thermostat.app.service;

import com.sid.thermostat.app.dto.ConfigDto;
import com.sid.thermostat.app.exceptions.RestException;
import com.sid.thermostat.app.protobuf.ConfigurationResponse;
import com.sid.thermostat.app.task.executor.RequestCache;

public interface ConfigurationService {
	public ConfigDto getConfigBySerialNo(String serialNo) throws RestException;

	public ConfigDto pushConfigForSerialNo(ConfigDto configDto, String serialNo) throws RestException;
	
	public void processConfigurationResponse(ConfigurationResponse configResponse, RequestCache requestCache);
}
