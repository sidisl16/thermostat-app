package com.sid.thermostat.app.task.executor;

public abstract class SyncTask<V> implements Task<V> {
	private RequestCache requestCache;

	protected RequestCache getRequestCache() {
		return requestCache;
	}
}