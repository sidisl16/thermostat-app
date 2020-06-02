package com.sid.thermostat.app.mongo.entites;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "device_data")
public class DeviceData {

	@Id
	private String id;

	private float value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
}
