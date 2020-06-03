package com.sid.thermostat.app.service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sid.thermostat.app.mongo.dal.DeviceDataDAL;
import com.sid.thermostat.app.mongo.entites.Device;
import com.sid.thermostat.app.mongo.entites.Event;
import com.sid.thermostat.app.mongo.repositories.DeviceRepository;
import com.sid.thermostat.app.protobuf.Data;

@Component
public class DeviceDataServiceImpl implements DeviceDataService {

	private static Logger logger = Logger.getLogger(DeviceDataServiceImpl.class.getName());

	@Autowired
	private DeviceDataDAL deviceDataDal;

	@Autowired
	private DeviceRepository deviceRepository;

	@Override
	public void addDeviceData(Data data) {
		Optional<Device> optionalDevice = deviceRepository.findBySerialNo(data.getSerialNo());
		if (optionalDevice.isPresent()) {
			logger.info("Processing data for serial no [" + data.getSerialNo() + "]");
			Event event = getEvent(data);
			deviceDataDal.upsertData(optionalDevice.get(), event);
		} else {
			logger.log(Level.WARNING, "Serial No not found [" + data.getSerialNo() + "]");
		}
	}

	private Event getEvent(Data data) {
		Event event = new Event();
		event.setTemperature(data.getTemperature());
		event.setTimestamp(data.getDeviceTime());
		return event;
	}

}
