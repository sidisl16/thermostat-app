package com.sid.thermostat.app.mongo.entites;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "device_data")
public class DeviceData {

	@Id
	private ObjectId id;

	@Field(value = "device_id")
	private ObjectId deviceId;

	@Field(value = "day")
	private Long day;

	@Field(value = "first")
	private Long first;

	@Field(value = "last")
	private Long last;

	@Field(value = "samples")
	private Integer samples;

	@Field(value = "events")
	private List<Event> events;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getDeviceId() {
		return deviceId;
	}

	public void setDevice_id(ObjectId deviceId) {
		this.deviceId = deviceId;
	}

	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}

	public Long getFirst() {
		return first;
	}

	public void setFirst(Long first) {
		this.first = first;
	}

	public Long getLast() {
		return last;
	}

	public void setLast(Long last) {
		this.last = last;
	}

	public Integer getSamples() {
		return samples;
	}

	public void setSamples(Integer samples) {
		this.samples = samples;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
}