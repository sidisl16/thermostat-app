package com.sid.thermostat.app.task.executor;

public abstract class AsyncTask implements Task<Void> {

	@Override
	public Void defineTask() throws InterruptedException {
		defineAsyncTask();
		return null;
	}

	protected abstract void defineAsyncTask();
}
