package com.sid.thermostat.app.outbound.message.template;

import java.util.concurrent.ExecutorService;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public abstract class OutboundMessageTemplate {

	protected static final int DEFAULT_PUBLISHER_THREAD = 10;
	protected ExecutorService executorService;
	protected MqttClient mqttClient;

	public void execute(PublishRequest request) {
		executorService.submit(() -> {
			try {
				mqttClient.publish(request.getTopic(), request.getPayload(), request.getQos(), request.isRetain());
			} catch (MqttException e) {
				e.printStackTrace();
			}
		});
	}
	// public abstract FailOverStrategy getFailOverStrategy();

	public abstract ExecutorService getExecutor();

	public abstract MqttClient getMqttClient();

	public void publish(PublishRequest request) {
		executorService = getExecutor();
		mqttClient = getMqttClient();
		if (executorService == null || mqttClient == null || request == null) {
			throw new IllegalArgumentException("Missing parameters.");
		}
		execute(request);
	}
}
