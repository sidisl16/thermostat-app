package com.sid.thermostat.app.mongo.entites;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Config {

	@Id
	private ObjectId id;

	@Field(value = "config_topic")
	private String configTopic;

	@Field(value = "data_topic")
	private String dataTopic;

	@Field(value = "data_interval")
	private Integer dataInterval;

	@Field(value = "status")
	private Status status;

	@Field(value = "retry_attempt")
	private Integer retryAttempt;

	@Field(value = "created_at")
	private Long createdAt;

	@Field(value = "last_modified")
	private Long lastModified;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getConfigTopic() {
		return configTopic;
	}

	public void setConfigTopic(String configTopic) {
		this.configTopic = configTopic;
	}

	public String getDataTopic() {
		return dataTopic;
	}

	public void setDataTopic(String dataTopic) {
		this.dataTopic = dataTopic;
	}

	public Integer getDataInterval() {
		return dataInterval;
	}

	public void setDataInterval(Integer dataInterval) {
		this.dataInterval = dataInterval;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getRetryAttempt() {
		return retryAttempt;
	}

	public void setRetryAttempt(Integer retryAttempt) {
		this.retryAttempt = retryAttempt;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Long getLastModified() {
		return lastModified;
	}

	public void setLastModified(Long lastModified) {
		this.lastModified = lastModified;
	}
}