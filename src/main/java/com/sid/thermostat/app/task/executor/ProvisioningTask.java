package com.sid.thermostat.app.task.executor;

import com.sid.thermostat.app.protobuf.ProvisioningRequest;

public class ProvisioningTask extends AsyncTask {

	private ProvisioningRequest request;

	public ProvisioningTask(ProvisioningRequest request) {
		this.request = request;
	}

	@Override
	protected void defineAsyncTask() {
		
	}

}
