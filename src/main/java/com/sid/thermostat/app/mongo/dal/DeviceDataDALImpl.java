package com.sid.thermostat.app.mongo.dal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.client.result.UpdateResult;
import com.sid.thermostat.app.mongo.entites.Device;
import com.sid.thermostat.app.mongo.entites.DeviceData;
import com.sid.thermostat.app.mongo.entites.Event;

@Component
public class DeviceDataDALImpl implements DeviceDataDAL {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public UpdateResult upsertData(Device device, Event event) {

		Criteria criteria = Criteria.where("device_id").is(device.getId()).and("day").is(getCurrentdateMillis());
		Query query = new Query(criteria);

		Update update = new Update().push("events", event).inc("samples", 1).set("last", (long) event.getTimestamp());

		return mongoTemplate.upsert(query, update, DeviceData.class);
	}

	private long getCurrentdateMillis() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		try {
			date = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

}
