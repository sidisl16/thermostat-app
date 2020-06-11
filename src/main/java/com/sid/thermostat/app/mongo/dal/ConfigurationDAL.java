package com.sid.thermostat.app.mongo.dal;

import java.util.Optional;

import com.sid.thermostat.app.mongo.entites.Config;

public interface ConfigurationDAL {
	public Optional<Config> getConfigBySerialNo(String serialNo); 
}
