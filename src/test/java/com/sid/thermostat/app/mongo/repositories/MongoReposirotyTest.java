package com.sid.thermostat.app.mongo.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.sid.thermostat.app.ThermostatAppApplication;
import com.sid.thermostat.app.mongo.entites.Config;
import com.sid.thermostat.app.mongo.entites.Device;
import com.sid.thermostat.app.mongo.entites.DeviceData;
import com.sid.thermostat.app.mongo.entites.Event;
import com.sid.thermostat.app.mongo.entites.Status;

@SpringBootTest
@EnableAutoConfiguration
public class MongoReposirotyTest extends ThermostatAppApplication {

	@Autowired
	private ConfigurationRepository configRepo;

	@Autowired
	private DeviceRepository deviceRepo;

	@Autowired
	private DeviceDataRepository dataRepo;

	private ObjectId deviceId;
	private ObjectId configId;
	private ObjectId dataId;

	@Test
	public void testDevicePersistance() {
		Config config = new Config();
		config.setConfigTopic("/inbound/configuration/SERIAL2R345");
		config.setCreatedAt(System.currentTimeMillis());
		config.setDataInterval(300);
		config.setDataTopic("/inbound/data/SERIAL2R345");
		config.setLastModified(System.currentTimeMillis());
		config.setRetryAttempt(0);
		config.setStatus(Status.COMPLETED);

		config = configRepo.save(config);

		configId = config.getId();

		assertNotNull(configId);

		Device device = new Device();
		device.setIpAddress("192.168.2.2");
		device.setMacAddress("AS:34:D5:G8:P0");
		device.setSerialNo("SERIAL2R345");
		device.setConfig(config);

		device = deviceRepo.save(device);

		deviceId = device.getId();

		assertNotNull(deviceId);
	}

	@Test
	public void testDeviceDataPersistance() throws ParseException {

		Long first = System.currentTimeMillis();

		Event event1 = new Event();
		event1.setTimestamp(first);
		event1.setTemperature(21f);
		Long last = System.currentTimeMillis();
		Event event2 = new Event();
		event2.setTimestamp(last);
		event2.setTemperature(31f);

		List<Event> events = new ArrayList<>();
		events.add(event1);
		events.add(event2);

		DeviceData data = new DeviceData();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = sdf.parse(sdf.format(new Date()));
		data.setDay(date.getTime());

		if (deviceId != null) {
			data.setDevice_id(deviceId);
		} else {
			data.setDevice_id(new ObjectId(1, 10));
		}

		data.setEvents(events);
		data.setFirst(first);
		data.setLast(last);
		data.setSamples(2);

		data = dataRepo.save(data);

		dataId = data.getId();
	}

	@AfterEach
	public void cleanUp() {

		if (configId != null) {
			configRepo.deleteById(configId);
		}

		if (deviceId != null) {
			deviceRepo.deleteById(deviceId);
		}

		if (dataId != null) {
			dataRepo.deleteById(dataId);
		}
	}
}