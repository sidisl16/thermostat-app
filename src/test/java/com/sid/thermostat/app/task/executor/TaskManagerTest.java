package com.sid.thermostat.app.task.executor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sid.thermostat.app.ThermostatAppApplicationTests;
import com.sid.thermostat.app.mongo.repositories.DeviceRepository;

public class TaskManagerTest extends ThermostatAppApplicationTests {

	@Autowired
	private TaskManager manager;

	@Test
	public void testSyncTaskExecuteMethod() {

		manager.execute(new SyncTask<String>() {

			@Override
			public String defineTask() {
				return "Testing";
			}
		}, new ResponseCallback<String>() {

			@Override
			public void onSucess(String object) {
				assertEquals(object, "Testing");
			}

			@Override
			public void onFailure(Throwable throwable) {
			}
		});
	}

}
