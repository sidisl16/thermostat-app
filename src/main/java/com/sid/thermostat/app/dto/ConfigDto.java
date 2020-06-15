package com.sid.thermostat.app.dto;

import com.sid.thermostat.app.mongo.entites.Status;

public class ConfigDto {

	private String inConfigTopic;
	private String outConfigTopic;
	private String inDataTopic;
	private Integer dataInterval;
	private Status status;
	private Integer retryAttempt;
	private Long createdAt;
	private Long lastModified;

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
}