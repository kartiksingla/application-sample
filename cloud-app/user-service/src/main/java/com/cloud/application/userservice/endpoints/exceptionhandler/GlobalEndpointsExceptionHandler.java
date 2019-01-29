package com.cloud.application.userservice.endpoints.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cloud.application.userservice.exceptions.UserNotExistsException;
import com.cloud.application.userservice.exceptions.model.ErrorResponseDto;

@ControllerAdvice
public class GlobalEndpointsExceptionHandler {

	@ExceptionHandler(UserNotExistsException.class)
	private ResponseEntity<ErrorResponseDto> userDoesNotExist(UserNotExistsException ex) {
		ErrorResponseDto errorDto = new ErrorResponseDto("does not exist",ex.getMessage());
		return new ResponseEntity<ErrorResponseDto>(errorDto, HttpStatus.NOT_FOUND);
	}
}
