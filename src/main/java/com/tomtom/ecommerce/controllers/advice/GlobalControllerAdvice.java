package com.tomtom.ecommerce.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tomtom.ecommerce.beans.ErrorPayload;
import com.tomtom.ecommerce.exceptions.InvalidProductRequestedException;
import com.tomtom.ecommerce.exceptions.InvalidUserDetailFoundException;
import com.tomtom.ecommerce.exceptions.JwtAuthorizationFailedException;
import com.tomtom.ecommerce.exceptions.OperationFailureException;

@ControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(OperationFailureException.class)
	public ResponseEntity<ErrorPayload> handelInvalidUser(OperationFailureException e, WebRequest req) {
		ErrorPayload er = new ErrorPayload();
		er.setCause(e.getMessage());
		er.setMessage("Execution error");
		return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(JwtAuthorizationFailedException.class)
	public ResponseEntity<ErrorPayload> handelInvalidUser(JwtAuthorizationFailedException e, WebRequest req) {
		ErrorPayload er = new ErrorPayload();
		er.setCause(e.getMessage());
		er.setMessage("User authorization request malformed");
		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(InvalidUserDetailFoundException.class)
	public ResponseEntity<ErrorPayload> handelInvalidUser(InvalidUserDetailFoundException e, WebRequest req) {
		ErrorPayload er = new ErrorPayload();
		er.setCause(e.getMessage());
		er.setMessage("User details expired/invaliated");
		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(InvalidProductRequestedException.class)
	public ResponseEntity<ErrorPayload> handelInvalidUser(InvalidProductRequestedException e, WebRequest req) {
		ErrorPayload er = new ErrorPayload();
		er.setCause(e.getMessage());
		er.setMessage("Product details not found in store");
		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CloneNotSupportedException.class)
	public ResponseEntity<ErrorPayload> handelInvalidUser(CloneNotSupportedException e, WebRequest req) {
		ErrorPayload er = new ErrorPayload();
		er.setCause(e.getMessage());
		er.setMessage("Technical error");
		return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
