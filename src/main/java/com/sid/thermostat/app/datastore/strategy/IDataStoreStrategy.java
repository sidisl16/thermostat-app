package com.sid.thermostat.app.datastore.strategy;

import com.sid.thermostat.app.protobuf.Data;

public interface IDataStoreStrategy {
	public void persistData(final Data data) throws DataStoreException;
}
