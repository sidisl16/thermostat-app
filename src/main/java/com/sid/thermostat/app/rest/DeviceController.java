package com.sid.thermostat.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sid.thermostat.app.dto.DeviceDto;
import com.sid.thermostat.app.service.DeviceService;

@RestController
@RequestMapping(value = "/devices")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeviceDto>> getAllDevice() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(deviceService.getAllDevices());
	}

	@GetMapping(value = "/{serialno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DeviceDto> getDeviceBySerialNo(@PathVariable(value = "serialno") String serialNo)
			throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(deviceService.getDeviceBySerialNo(serialNo));
	}

}
