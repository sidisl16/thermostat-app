package com.sid.thermostat.app.inbound.message.observers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.protobuf.GeneratedMessageV3;
import com.sid.thermostat.app.message.processor.MessageProcessor;
import com.sid.thermostat.app.protobuf.ProvisioningRequest;

@Component
public class DeviceProvisioningObserver implements MessageObserver {

	private static Logger logger = Logger.getLogger(DeviceProvisioningObserver.class.getName());
	private static final String PROVISIONING_TOPIC_FILTER = "/inbound/provisioning/+";
	private static final Class<? extends GeneratedMessageV3> MESSAGE_CLASS = ProvisioningRequest.class;

	// Used to discard duplicate messages if already present in this Map
	private Map<String, Integer> lastMessage;

	@Autowired
	private MessageProcessor messageProcessor;

	@Autowired
	public DeviceProvisioningObserver(MqttTopicSubject subject) {
		lastMessage = new ConcurrentHashMap<>();
		subject.registerObserver(this);
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println(">>>>>>>>>>>>>" + new String(message.getPayload()));
		logger.log(Level.INFO, "Message recieved, message[id:" + message.getId() + ", isDup:" + message.isDuplicate()
				+ ", QOS" + message.getQos() + "]");
		// Logic to handle QOS 1 duplicate message
		if (message.isDuplicate() && lastMessage.containsKey(topic) && lastMessage.get(topic).equals(message.getId())) {
			logger.log(Level.WARNING,
					"Duplicate message recieved, discarding message. messageId[" + message.getId() + "]");
			return;
		} else {
			lastMessage.put(topic, message.getId());
		}
		try {
			process(message.getPayload());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Unknown message recieved, messageId[" + message.getId() + "] " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void process(byte[] payload) throws Exception {
		messageProcessor.process(payload, MESSAGE_CLASS);
	}

	@Override
	public String getTopicFilter() {
		return PROVISIONING_TOPIC_FILTER;
	}
}
