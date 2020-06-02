package com.sid.thermostat.app.observers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.protobuf.GeneratedMessageV3;
import com.sid.thermostat.app.message.processor.MessageProcessor;
import com.sid.thermostat.app.protobuf.Data;

@Component
public class DeviceDataObserver implements MessageObserver {

	private static Logger logger = Logger.getLogger(DeviceDataObserver.class.getName());
	// Define type of data for each topic in proto
	private static final String DATA_TOPIC_FILTER = "/data/+";
	private static final Class<? extends GeneratedMessageV3> MESSAGE_CLASS = Data.class;

	@Autowired
	private MessageProcessor messageProcessor;
	// Used to discard duplicate messages if already present in this Map
	private Map<String, Integer> lastMessage;

	@Autowired
	public DeviceDataObserver(MqttTopicSubject subject) {
		lastMessage = new ConcurrentHashMap<>();
		subject.registerObserver(this);
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		logger.log(Level.INFO, "Message recieved, message[id:" + message.getId() + ", isDup:" + message.isDuplicate()
				+ ", QOS" + message.getQos() + "]");
		// Logic to handle QOS 1 duplicate message
		if (message.isDuplicate() && lastMessage.containsKey(topic) && lastMessage.get(topic).equals(message.getId())) {
			logger.log(Level.WARNING, "Duplicate message recieved, discarding message.");
			return;
		} else {
			lastMessage.put(topic, message.getId());
		}

		try {
			process(message.getPayload());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Unknown message recieved, messageId[" + message.getId() + "]");
		}
	}

	private void process(byte[] payload) throws Exception {
		messageProcessor.process(payload, MESSAGE_CLASS);
	}

	@Override
	public String getTopicFilter() {
		return DATA_TOPIC_FILTER;
	}
}
