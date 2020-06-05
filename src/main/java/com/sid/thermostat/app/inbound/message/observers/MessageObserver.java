package com.sid.thermostat.app.inbound.message.observers;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;

public interface MessageObserver extends IMqttMessageListener {
	public String getTopicFilter();
}
