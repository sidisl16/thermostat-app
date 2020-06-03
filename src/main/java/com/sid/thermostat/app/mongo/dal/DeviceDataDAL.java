package com.sid.thermostat.app.mongo.dal;

import com.sid.thermostat.app.mongo.entites.Device;
import com.sid.thermostat.app.mongo.entites.Event;

public interface DeviceDataDAL {
	public void saveOrUpsertData(Device device, Event event);
}
