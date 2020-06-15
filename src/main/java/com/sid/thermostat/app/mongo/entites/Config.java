package com.sid.thermostat.app.mongo.entites;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Config {

	@Id
	private ObjectId id;

	@Field(value = "in_config_topic")
	private String inConfigTopic;

	@Field(value = "out_config_topic")
	private String outConfigTopic;

	@Field(value = "in_data_topic")
	private String inDataTopic;

	@Field(value = "in_data_interval")
	private Integer dataInterval;

	@Field(value = "status")
	private Status status;

	@Field(value = "retry_attempt")
	private Integer retryAttempt;

	@Field(value = "created_at")
	private Long createdAt;

	@Field(value = "last_modified")
	private Long lastModified;

	@Field(value = "new_config")
	private Config newConfig;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getInConfigTopic() {
		return inConfigTopic;
	}

	public void setInConfigTopic(String inConfigTopic) {
		this.inConfigTopic = inConfigTopic;
	}

	public String getOutConfigTopic() {
		return outConfigTopic;
	}

	public void setOutConfigTopic(String outConfigTopic) {
		this.outConfigTopic = outConfigTopic;
	}
	
	public String getInDataTopic() {
		return inDataTopic;
	}

	public void setInDataTopic(String inDataTopic) {
		this.inDataTopic = inDataTopic;
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

	public Config getNewConfig() {
		return newConfig;
	}

	public void setNewConfig(Config newConfig) {
		this.newConfig = newConfig;
	}
}