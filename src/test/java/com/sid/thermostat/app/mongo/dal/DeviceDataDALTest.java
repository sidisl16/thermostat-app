package com.sid.thermostat.app.mongo.dal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.client.result.UpdateResult;
import com.sid.thermostat.app.ThermostatAppApplicationTests;
import com.sid.thermostat.app.mongo.entites.Device;
import com.sid.thermostat.app.mongo.entites.Event;
import com.sid.thermostat.app.mongo.repositories.DeviceDataRepository;

public class DeviceDataDALTest extends ThermostatAppApplicationTests {

	@Autowired
	private DeviceDataDAL deviceDataDAL;

	@Autowired
	private DeviceDataRepository deviceDataRepository;

	private ObjectId deviceDataId;

	@Test
	public void testUpsertDeviceData() {
		ObjectId deviceId = new ObjectId();
		Device device = new Device();
		device.setId(deviceId);

		Event event = new Event();
		event.setTemperature(43.0f);
		event.setTimestamp(System.currentTimeMillis());

		UpdateResult result = deviceDataDAL.upsertData(device, event);
		deviceDataId = result.getUpsertedId().asObjectId().getValue();
		assertNotNull(result.getUpsertedId());

		UpdateResult result2 = deviceDataDAL.upsertData(device, event);
		assertEquals(result2.getMatchedCount(), 1);
	}

	@AfterEach
	public void cleanUp() {
		if (deviceDataId != null) {
			deviceDataRepository.deleteById(deviceDataId);
		}

	}

}
