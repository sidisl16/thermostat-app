package com.sid.thermostat.app.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sid.thermostat.app.ThermostatAppApplicationTests;
import com.sid.thermostat.app.dto.ConfigDto;
import com.sid.thermostat.app.mongo.entites.Config;

public class EntityAndDtoConversionUtilTest extends ThermostatAppApplicationTests {

	@Test
	public void testCopyIgnoreNull() {
		Config config = new Config();
		config.setInConfigTopic("/topic/randomconfig");
		config.setDataInterval(10);
		config.setInDataTopic("/topic/randomdata");

		ConfigDto configDto = new ConfigDto();
		configDto.setInConfigTopic("/topic/randomconfig1");
		config.setDataInterval(12);

		EntityAndDtoConversionUtil.copyIgnoreNull(configDto, config);

		assertEquals("/topic/randomconfig1", config.getInConfigTopic());
		assertEquals(12, config.getDataInterval());
		assertEquals("/topic/randomdata", config.getInDataTopic());
	}
}
