package com.sid.thermostat.app.mongo.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.sid.thermostat.app.mongo.entites.Device;
import com.sid.thermostat.app.mongo.entites.DeviceData;
import com.sid.thermostat.app.mongo.entites.Event;

public class DeviceDataDALImpl implements DeviceDataDAL {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void saveOrUpsertData(Device device, Event event) {
		//mongoTemplate.upsert(query, update, DeviceData.class);
	}

}
