package com.sid.thermostat.app.task.executor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

import com.google.protobuf.GeneratedMessageV3;

public enum RequestCache {

	INSTANCE;

	private Map<String, SynchronousQueue<GeneratedMessageV3>> map;

	private RequestCache() {
		map = new ConcurrentHashMap<>();
	}

	public void put(String requestId, SynchronousQueue<GeneratedMessageV3> response) {
		map.put(requestId, response);
	}

	public SynchronousQueue<GeneratedMessageV3> get(String requestId) {
		return map.get(requestId);
	}

	public boolean containsKey(String requestId) {
		return map.containsKey(requestId);
	}

	public void remove(String requestId) {
		map.remove(requestId);
	}
	
}