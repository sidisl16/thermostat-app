package com.sid.thermostat.app.task.executor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.tomcat.util.collections.SynchronizedQueue;

import com.google.protobuf.GeneratedMessageV3;

public enum RequestCache {

	INSTANCE;

	private Map<String, SynchronizedQueue<? extends GeneratedMessageV3>> map;

	private RequestCache() {
		map = new ConcurrentHashMap<>();
	}

	public void put(String requestId, SynchronizedQueue<? extends GeneratedMessageV3> response) {
		map.put(requestId, response);
	}

	public SynchronizedQueue<? extends GeneratedMessageV3> get(String requestId) {
		return map.get(requestId);
	}

	public void remove(String requestId) {
		map.remove(requestId);
	}

}
