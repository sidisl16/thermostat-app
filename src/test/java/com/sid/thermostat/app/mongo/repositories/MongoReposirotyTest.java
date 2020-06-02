package com.sid.thermostat.app.mongo.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.sid.thermostat.app.ThermostatAppApplication;
import com.sid.thermostat.app.mongo.entites.Config;
import com.sid.thermostat.app.mongo.entites.Device;
import com.sid.thermostat.app.mongo.entites.Status;

@SpringBootTest
@EnableAutoConfiguration
public class MongoReposirotyTest extends ThermostatAppApplication {

	@Autowired
	private ConfigurationRepository configRepo;

	@Autowired
	private DeviceRepository deviceRepo;

	@Test
	public void testDevicePersistance() {
		Config config = new Config();
		config.setConfigTopic("/configuration/SERIAL2R345");
		config.setCreatedAt(System.currentTimeMillis());
		config.setDataInterval(300);
		config.setDataTopic("/data/SERIAL2R345");
		config.setLastModified(System.currentTimeMillis());
		config.setRetryAttempt(0);
		config.setStatus(Status.COMPLETED);

		config = configRepo.save(config);

		Device device = new Device();
		device.setIpAddress("192.168.2.2");
		device.setMacAddress("AS:34:D5:G8:P0");
		device.setSerialNo("SERIAL2R345");
		device.setConfig(config);

		device = deviceRepo.save(device);
	}

}
