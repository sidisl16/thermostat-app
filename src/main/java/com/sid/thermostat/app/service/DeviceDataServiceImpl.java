package com.sid.thermostat.app.service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sid.thermostat.app.datastore.strategy.DataStoreException;
import com.sid.thermostat.app.datastore.strategy.DataStoreManager;
import com.sid.thermostat.app.mongo.entites.Device;
import com.sid.thermostat.app.mongo.repositories.DeviceRepository;
import com.sid.thermostat.app.protobuf.Data;

@Component
public class DeviceDataServiceImpl implements DeviceDataService {

	private static Logger logger = Logger.getLogger(DeviceDataServiceImpl.class.getName());

	@Autowired
	private DataStoreManager dataStoreManager;

	@Autowired
	private DeviceRepository deviceRepository;

	@Override
	public void addDeviceData(Data data) {
		Optional<Device> optionalDevice = deviceRepository.findBySerialNo(data.getSerialNo());
		if (optionalDevice.isPresent()) {
			logger.info("Processing data for serial no [" + data.getSerialNo() + "]");
			try {
				dataStoreManager.persistData(data);
			} catch (DataStoreException e) {
				logger.log(Level.SEVERE, "Unable to processing data for serial no [" + data.getSerialNo()
						+ "], reason [" + e.getMessage() + "]");
				e.printStackTrace();
			}
		} else {
			logger.log(Level.WARNING, "Serial No not found [" + data.getSerialNo() + "]");
		}
	}
}