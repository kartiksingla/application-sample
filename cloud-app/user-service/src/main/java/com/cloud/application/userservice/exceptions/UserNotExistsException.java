package com.cloud.application.userservice.exceptions;

public class UserNotExistsException extends RuntimeException {

	private static final long serialVersionUID = -364117033797858526L;

	public UserNotExistsException(String message) {
		super(message);
	}
	
	
}
