package com.sid.thermostat.app.message.processor;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.protobuf.GeneratedMessageV3;
import com.sid.thermostat.app.mongo.entites.DeviceData;
import com.sid.thermostat.app.mongo.repositories.DeviceDataRepository;
import com.sid.thermostat.app.protobuf.Data;

@Component
public class MessageProcessorImpl implements MessageProcessor {

	private static Logger logger = Logger.getLogger(MessageProcessorImpl.class.getName());
	
	@Autowired
	private DeviceDataRepository repo;

	@Override
	public void process(byte[] payload, Class<? extends GeneratedMessageV3> payloadType) throws Exception {
		logger.info("Serializing message type[" + payloadType.getName() + "]");
		Method staticDeserializationMethod = payloadType.getMethod("parseFrom", byte[].class);
		GeneratedMessageV3 message = (GeneratedMessageV3) staticDeserializationMethod.invoke(null, payload);
		process(message);
	}

	private void process(GeneratedMessageV3 message) {
		if (message instanceof Data) {
			Data data = (Data) message;
			DeviceData d = new DeviceData();
			d.setValue(data.getTemperature());
			repo.save(d);
			logger.log(Level.INFO, "Data [Serial no:" + data.getSerialNo() + "]");
		}
	}

}
