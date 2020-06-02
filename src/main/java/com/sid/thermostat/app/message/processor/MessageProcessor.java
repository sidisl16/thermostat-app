package com.sid.thermostat.app.message.processor;

import com.google.protobuf.GeneratedMessageV3;

public interface MessageProcessor {
	public void process(byte[] payload, Class<? extends GeneratedMessageV3> payloadType) throws Exception;
}
