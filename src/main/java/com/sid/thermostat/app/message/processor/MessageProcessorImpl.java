package com.sid.thermostat.app.message.processor;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.protobuf.GeneratedMessageV3;
import com.sid.thermostat.app.protobuf.Data;
import com.sid.thermostat.app.protobuf.ProvisioningRequest;
import com.sid.thermostat.app.service.DeviceDataService;
import com.sid.thermostat.app.service.DeviceService;

@Component
public class MessageProcessorImpl implements MessageProcessor {

	private static Logger logger = Logger.getLogger(MessageProcessorImpl.class.getName());

	@Autowired
	private DeviceDataService deviceDataService;

	@Autowired
	private DeviceService deviceService;

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
			deviceDataService.addDeviceData(data);

		} else if (message instanceof ProvisioningRequest) {

			ProvisioningRequest provisioningRequest = (ProvisioningRequest) message;
			deviceService.provisionDevice(provisioningRequest);
		}
		
	}

}
