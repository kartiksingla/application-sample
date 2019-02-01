package com.cloud.application.authserver.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 3520084242692927242L;

	public UserNotFoundException(String username) {
		super("User not found with username : " + username);
	}
}
