package com.tomtom.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tomtom.ecommerce.beans.Cart;
import com.tomtom.ecommerce.beans.ErrorPayload;
import com.tomtom.ecommerce.beans.ResponsePayload;
import com.tomtom.ecommerce.beans.SuccessPayload;
import com.tomtom.ecommerce.exceptions.InvalidUserDetailFoundException;
import com.tomtom.ecommerce.exceptions.JwtAuthorizationFailedException;
import com.tomtom.ecommerce.repositories.impl.CustomerProductRepositoryManagerImpl;
import com.tomtom.ecommerce.security.authorization.JwtAuthorizationManager;
import com.tomtom.ecommerce.service.CustomerCheckoutServiceProvider;

import io.jsonwebtoken.Claims;

@Service
public class CustomerCheckoutServiceProviderImpl implements CustomerCheckoutServiceProvider {
	@Autowired
	CustomerProductRepositoryManagerImpl customerProductRepositoryManagerImpl;

	@Autowired
	JwtAuthorizationManager jwtAuthorizationManager;

	@Override
	public ResponseEntity<String> checkout(String userId)
			throws InvalidUserDetailFoundException, JwtAuthorizationFailedException {
		// verify user -> sign the cart - return jwt
		Cart cart = customerProductRepositoryManagerImpl.getAllProductsFromCart(userId);
		System.out.println("signing---" + cart.toString());
		String jwt = jwtAuthorizationManager.createJWT(cart.toString());
		if (null != jwt && !jwt.isEmpty()) {
			return new ResponseEntity<String>(jwt, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Please try later", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<ResponsePayload> makePayment(String userId, String jwtData)
			throws JwtAuthorizationFailedException {
		Claims claim = jwtAuthorizationManager.decodeJWT(jwtData);
		// verify claim, customer data --> make payment
		if (null != claim && !claim.getSubject().isEmpty()) {
			SuccessPayload success = new SuccessPayload("payment done, happpy shopping");
			return new ResponseEntity<ResponsePayload>(success, HttpStatus.OK);
		}
		ErrorPayload error = new ErrorPayload("Payment failed, please retry");
		return new ResponseEntity<ResponsePayload>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}