package com.sid.thermostat.app.datastore.strategy;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sid.thermostat.app.protobuf.Data;
import com.sid.thermostat.app.task.executor.AsyncTask;
import com.sid.thermostat.app.task.executor.TaskManager;

@Component
public class DataStoreManager implements IDataStoreStrategy {

	private static Logger logger = Logger.getLogger(DataStoreManager.class.getName());

	// Injects all the beans which implements IDataStoreStrategy
	@Autowired
	private List<? extends IDataStoreStrategy> strategies;

	@Autowired
	private TaskManager taskManager;

	@Override
	public void persistData(final Data data) throws DataStoreException {
		for (IDataStoreStrategy strategy : strategies) {
			if (!(strategy instanceof DataStoreManager)) {
				logger.log(Level.INFO, "Processing data using strategy [" + strategy.getClass().getName() + "]");
				taskManager.execute(new AsyncTask() {

					@Override
					protected void defineAsyncTask() {
						try {
							strategy.persistData(data);
						} catch (DataStoreException e) {
							e.printStackTrace();
						}
					}
				});
			}
		}
	}

}