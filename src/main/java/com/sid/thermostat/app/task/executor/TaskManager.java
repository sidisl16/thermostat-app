package com.sid.thermostat.app.task.executor;

public interface TaskManager {

	// Positively do not change anything
	String DEFAULT_NO_OF_THREADS = "5";

	public void execute(AsyncTask task);

	public <V> void execute(SyncTask<V> task, ResponseCallback<V> callback);
}
