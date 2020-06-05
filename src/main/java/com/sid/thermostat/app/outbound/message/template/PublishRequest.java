package com.sid.thermostat.app.outbound.message.template;

public class PublishRequest {

	private String topic;
	private byte[] payload;
	private int qos = 1;
	private boolean retain = true;

	public PublishRequest(Builder builder) {
		this.topic = builder.topic;
		this.payload = builder.payload;
		this.qos = builder.qos;
		this.retain = builder.retain;
	}

	public String getTopic() {
		return topic;
	}

	public byte[] getPayload() {
		return payload;
	}

	public int getQos() {
		return qos;
	}

	public boolean isRetain() {
		return retain;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {
		private String topic;
		private byte[] payload;
		private int qos = 1;
		private boolean retain = true;

		public Builder setTopic(String topic) {
			this.topic = topic;
			return this;
		}

		public Builder setPayload(byte[] payload) {
			this.payload = payload;
			return this;
		}

		public Builder setQos(int qos) {
			this.qos = qos;
			return this;
		}

		public Builder setRetain(boolean retain) {
			this.retain = retain;
			return this;
		}

		public PublishRequest build() {
			return new PublishRequest(this);
		}
	}

}
