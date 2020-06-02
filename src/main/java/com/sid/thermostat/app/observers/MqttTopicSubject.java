package com.sid.thermostat.app.observers;

import java.util.List;

public interface MqttTopicSubject {
	public void registerObserver(MessageObserver observer);
	public List<MessageObserver> getAllObserver();
}
