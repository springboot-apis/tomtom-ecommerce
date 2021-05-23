package com.tomtom.ecommerce.exceptions;

public class JwtAuthorizationFailedException extends Exception  {

	public JwtAuthorizationFailedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtAuthorizationFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public JwtAuthorizationFailedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public JwtAuthorizationFailedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public JwtAuthorizationFailedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
