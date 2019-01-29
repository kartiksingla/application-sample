package com.cloud.application.userservice.exceptions.model;

public class ErrorResponseDto {

	private String requestStatus;
	
	private String errorMessage;

	public ErrorResponseDto(String requestStatus, String errorMessage) {
		super();
		this.requestStatus = requestStatus;
		this.errorMessage = errorMessage;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
