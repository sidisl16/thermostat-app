package com.sid.thermostat.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sid.thermostat.app.dto.ConfigDto;
import com.sid.thermostat.app.exceptions.RestException;
import com.sid.thermostat.app.service.ConfigurationService;

@RestController
@RequestMapping("/config")
public class ConfigurationController {

	@Autowired
	private ConfigurationService configService;

	@PostMapping(value = "/{serialno}/push")
	public ResponseEntity<ConfigDto> pushConfig(@RequestBody ConfigDto configDto,
			@PathVariable(value = "serialno") String serialNo) throws RestException {
		return ResponseEntity.status(HttpStatus.OK).body(configService.pushConfigForSerialNo(configDto, serialNo));
	}

	@GetMapping(value = "/{serialno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConfigDto> getConfigBySerialNo(@PathVariable(value = "serialno") String serialNo)
			throws RestException {
		return ResponseEntity.status(HttpStatus.OK).body(configService.getConfigBySerialNo(serialNo));
	}
}
