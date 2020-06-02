package com.sid.thermostat.app.observers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MqttTopicSubjectImpl implements MqttTopicSubject {

	private List<MessageObserver> observers;

	public MqttTopicSubjectImpl() {
		observers = new ArrayList<>();
	}

	@Override
	public void registerObserver(MessageObserver observer) {
		observers.add(observer);
	}

	@Override
	public List<MessageObserver> getAllObserver() {
		return this.observers;
	}

}
