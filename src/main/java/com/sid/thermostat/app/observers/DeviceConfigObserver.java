package com.sid.thermostat.app.observers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceConfigObserver implements MessageObserver {

	private static Logger logger = Logger.getLogger(DeviceConfigObserver.class.getName());
	private static final String CONFIG_TOPIC_FILTER = "/configuration/777";

	// Used to discard duplicate messages if already present in this Map
	private Map<String, Integer> lastMessage;

	@Autowired
	public DeviceConfigObserver(MqttTopicSubject subject) {
		lastMessage = new ConcurrentHashMap<>();
		subject.registerObserver(this);
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println(this.toString() + "topic [" + topic + "] Message recieved ["
				+ new String(message.getPayload()) + "] " + message.isDuplicate() + " " + message.getId());
	}

	@Override
	public String getTopicFilter() {
		return CONFIG_TOPIC_FILTER;
	}
}
