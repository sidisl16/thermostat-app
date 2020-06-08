package com.sid.thermostat.app.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sid.thermostat.app.dto.ConfigDto;

@RestController
@RequestMapping("/config")
public class ConfigurationController {

	@PostMapping(value = "/{serialno}/push")
	public void pushConfig(@RequestBody ConfigDto configDto, @PathVariable(value = "serialno") String serialNo) {

	}

	@GetMapping(value = "/{serialno}/", produces = MediaType.APPLICATION_JSON_VALUE)
	public void getConfigBySerialNo(@RequestBody ConfigDto configDto,
			@PathVariable(value = "serialno") String serialNo) {
		
		
	}
}
