package com.sid.thermostat.app.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		ProvisioningRequest provisioningRequest = ProvisioningRequest.newBuilder().setSerialNo("SERIAL456790")
				.setMacAddress("AC:DC:FS:22:34:4F").setIpAddress("192.168.255.2").build();
		mqttClient.subscribe("/outbound/provisioning/SERIAL456790", 0, new IMqttMessageListener() {
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				ProvisioningResponse response = ProvisioningResponse.parseFrom(message.getPayload());
				System.out.println(">>>>>>>>>>>>>>>>>>" + response.getConfigTopic());
				assertEquals("/inbound/configuration/SERIAL456790", response.getConfigTopic());
			}
		});
		try {
			mqttClient.publish("/inbound/provisioning/SERIAL456790",
					new MqttMessage(provisioningRequest.toByteArray()));
		} catch (MqttException e) {
			e.printStackTrace();
		}
		Thread.sleep(30000);
	}
}
