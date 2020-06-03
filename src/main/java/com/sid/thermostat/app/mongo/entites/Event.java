package com.sid.thermostat.app.mongo.entites;

public class Event {

	private Long timestamp;

	private float temperature;

	public float getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
}