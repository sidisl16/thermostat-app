package com.sid.thermostat.app.exceptions;

import org.springframework.http.HttpStatus;

public class RestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;
	private String message;

	public RestException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
