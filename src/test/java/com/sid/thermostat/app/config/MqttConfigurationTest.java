package com.sid.thermostat.app.config;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.sid.thermostat.app.ThermostatAppApplication;
import com.sid.thermostat.app.protobuf.Data;
import com.sid.thermostat.app.protobuf.Data.Unit;

@SpringBootTest
@EnableAutoConfiguration
public class MqttConfigurationTest extends ThermostatAppApplication {

	@Autowired
	private MqttClient mqttClient;

	@Test
	public void testConnectMqttServer() throws MqttException {
		assertTrue(mqttClient.isConnected());
	}

	@Test
	public void testTopicPublish() throws MqttPersistenceException, MqttException, InterruptedException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		AtomicInteger counter = new AtomicInteger();
		Data data = Data.newBuilder().setDeviceTime(System.currentTimeMillis()).setSerialNo("123")
				.setTemperature(10.23f).setUnit(Unit.CELCIUS).build();
		for (int i = 0; i < 5; i++) {
			executor.submit(() -> {
				try {
					mqttClient.publish("/data/123", new MqttMessage(data.toByteArray()));
				} catch (MqttException e) {
					e.printStackTrace();
				}
			});
		}
		Thread.sleep(10000);
		executor.shutdown();
	}
}
