package com.sid.thermostat.app.config;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sid.thermostat.app.ThermostatAppApplicationTests;
import com.sid.thermostat.app.protobuf.ProvisioningRequest;
import com.sid.thermostat.app.protobuf.ProvisioningResponse;

public class MqttConfigurationTest extends ThermostatAppApplicationTests {

	@Autowired
	private MqttClient mqttClient;

	@Test
	public void testConnectMqttServer() throws MqttException {
		assertTrue(mqttClient.isConnected());
	}

	@Test
	public void testProvisioningPublish() throws MqttPersistenceException, MqttException, InterruptedException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		ProvisioningRequest provisioningRequest = ProvisioningRequest.newBuilder().setSerialNo("SERIAL4567")
				.setIpAddress("192.168.255.2").build();
		for (int i = 0; i < 1; i++) {
			executor.submit(() -> {
				try {
					mqttClient.publish("/inbound/provisioning/SERIAL4567",
							new MqttMessage(provisioningRequest.toByteArray()));
				} catch (MqttException e) {
					e.printStackTrace();
				}
			});
		}
		mqttClient.subscribe("/outbound/provisioning/SERIAL4567", new IMqttMessageListener() {
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				ProvisioningResponse response = ProvisioningResponse.parseFrom(message.getPayload());
				 assertEquals("/inbound/configuration/SERIAL4567", response.getConfigTopic()	);
			}
		});
		Thread.sleep(10000);
		executor.shutdown();
	}
}
