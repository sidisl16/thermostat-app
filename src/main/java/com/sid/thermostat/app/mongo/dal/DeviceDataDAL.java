package com.sid.thermostat.app.mongo.dal;

import com.mongodb.client.result.UpdateResult;
import com.sid.thermostat.app.mongo.entites.Device;
import com.sid.thermostat.app.mongo.entites.Event;

public interface DeviceDataDAL {
	public UpdateResult upsertData(Device device, Event event);
}
