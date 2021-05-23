package com.tomtom.ecommerce.service;

import org.springframework.http.ResponseEntity;

import com.tomtom.ecommerce.beans.ResponsePayload;
import com.tomtom.ecommerce.exceptions.InvalidUserDetailFoundException;
import com.tomtom.ecommerce.exceptions.JwtAuthorizationFailedException;

public interface CustomerCheckoutServiceProvider {
	public ResponseEntity<ResponsePayload> makePayment(String userId, String jwtData) throws JwtAuthorizationFailedException;
	public ResponseEntity<String> checkout(String userId) throws InvalidUserDetailFoundException, JwtAuthorizationFailedException;
}
