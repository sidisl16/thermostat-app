package com.sid.thermostat.app.task.executor;

public abstract class SyncTask<V> implements Task<V> {

	protected RequestCache getRequestCache() {
		return RequestCache.INSTANCE;
	}
}