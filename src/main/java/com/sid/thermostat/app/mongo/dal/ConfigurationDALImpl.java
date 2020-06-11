package com.sid.thermostat.app.mongo.dal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.sid.thermostat.app.mongo.entites.Config;
import com.sid.thermostat.app.mongo.entites.Device;

@Component
public class ConfigurationDALImpl implements ConfigurationDAL {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Optional<Config> getConfigBySerialNo(String serialNo) {
		Query query = new Query(Criteria.where("serial_no").is(serialNo));		
		Device device = mongoTemplate.findOne(query, Device.class);
		Optional<Device> deviceOptional = Optional.ofNullable(device);
		return deviceOptional.isPresent() ? Optional.ofNullable(deviceOptional.get().getConfig())
				: Optional.ofNullable(null);
	}

}
