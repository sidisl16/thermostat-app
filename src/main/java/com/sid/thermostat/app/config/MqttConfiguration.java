package com.sid.thermostat.app.config;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.sid.thermostat.app.inbound.message.observers.MqttTopicSubject;

@Configuration
public class MqttConfiguration {

	@Autowired
	private Environment env;

	@Autowired
	private MqttTopicSubject subject;

	private static final Logger logger = Logger.getLogger(MqttConfiguration.class.getName());

	private static final String DEFAULT_SERVER = "tcp://localhost:1883";
	private static final String DEFAULT_CLIENT_ID = "mqtt-client-" + UUID.randomUUID().toString();
	private static final String DEFAULT_CONCURRENCY = "10";

	@Bean
	public MqttClient getMqttClient() throws MqttException {
		String serverURI = env.getProperty("mqtt.server.url", DEFAULT_SERVER);
		String clientId = env.getProperty("mqtt.client.id", DEFAULT_CLIENT_ID);
		logger.log(Level.INFO, "Creating mqtt client server[" + serverURI + "] clientId[" + clientId + "]");
		MqttClient mqttClient = new MqttClient(serverURI, clientId, new MqttDefaultFilePersistence(),
				Executors.newScheduledThreadPool(
						Integer.parseInt(env.getProperty("mqtt.client.concurrency", DEFAULT_CONCURRENCY))));
		logger.log(Level.INFO, "Mqtt Client[" + clientId + "] connecting to Server[" + serverURI + "]");
		try {
			mqttClient.connect(getMqttConnectionOption());
			if (mqttClient.isConnected()) {
				logger.log(Level.INFO, "Mqtt Client[" + clientId + "] connected to Server[" + serverURI + "]");
			}
			suscribeTopics(mqttClient);
		} catch (MqttException e) {
			logger.log(Level.SEVERE, "Mqtt Client[" + clientId + "] failed to connect Server[" + serverURI
					+ "], reason [" + e.getMessage() + "]");
			throw e;
		}
		return mqttClient;
	}

	private void suscribeTopics(MqttClient mqttClient) {
		subject.getAllObserver().forEach(observer -> {
			try {
				mqttClient.subscribeWithResponse(observer.getTopicFilter(), 1, observer);
			} catch (MqttException e) {
				logger.log(Level.SEVERE,
						"failed to suscribe topic[" + observer.getTopicFilter() + "], reason [" + e.getMessage() + "]");
				e.printStackTrace();
			}
		});
	}

	private MqttConnectOptions getMqttConnectionOption() {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setAutomaticReconnect(true);
		options.setCleanSession(false);
		options.setConnectionTimeout(10000);
		return options;
	}
}