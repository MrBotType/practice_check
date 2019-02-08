package com.cts.signup.rest;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cts.signup.bean.ErrorResponse;

public class ExceptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception) {

		LOGGER.info("START");
		LOGGER.error(exception.getMessage(), exception);
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
		ResponseEntity<ErrorResponse> responseEntity = null;
		if (exception instanceof ConstraintViolationException) {
			errorResponse.setReasonCode(HttpStatus.BAD_REQUEST.value());
			ConstraintViolationException constraintException = (ConstraintViolationException) exception;
			Set<ConstraintViolation<?>> setOfConstrainViolation = constraintException.getConstraintViolations();
			String errorMessage = "Input validation failed: ";
			for (ConstraintViolation<?> constraintViolation : setOfConstrainViolation) {
				errorMessage += constraintViolation.getMessageTemplate() + ",";
			}
			errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
			errorResponse.setErrorMessage(errorMessage);
			responseEntity = new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
		} else {
			errorResponse.setReasonCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setErrorMessage(exception.getMessage());
			responseEntity = new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.debug("ErrorResponse : {} ", errorResponse);
		LOGGER.debug("ResponseEntity<ErrorResponse> : {}", responseEntity);
		return responseEntity;
	}
}
