package com.sid.thermostat.app.task.executor;

import java.util.concurrent.Callable;

public interface Task<V> extends Callable<V> {

	@Override
	default V call() {
		return defineTask();
	}

	public V defineTask();
}
