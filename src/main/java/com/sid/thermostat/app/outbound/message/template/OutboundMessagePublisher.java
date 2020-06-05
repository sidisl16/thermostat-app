package com.sid.thermostat.app.outbound.message.template;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OutboundMessagePublisher extends OutboundMessageTemplate {

	@Autowired
	private MqttClient mqttClient;

	@Override
	public ExecutorService getExecutor() {
		return Executors.newFixedThreadPool(DEFAULT_PUBLISHER_THREAD);
	}

	@Override
	public MqttClient getMqttClient() {
		return mqttClient;
	}

}
