package com.sid.thermostat.app.inbound.message.observers;

import java.util.List;

public interface MqttTopicSubject {
	public void registerObserver(MessageObserver observer);
	public List<MessageObserver> getAllObserver();
}
