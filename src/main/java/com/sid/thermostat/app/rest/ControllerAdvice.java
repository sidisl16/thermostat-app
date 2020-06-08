package com.sid.thermostat.app.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sid.thermostat.app.exceptions.RestException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(RestException ex) {
		return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
	}
	
}
