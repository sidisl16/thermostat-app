package com.sid.thermostat.app.task.executor;


public interface ResponseCallback<V> {

	public void onSucess(V object);

	public void onFailure(Throwable throwable);
}
