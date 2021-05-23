package com.tomtom.ecommerce.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.tomtom.ecommerce.beans.Cart;
import com.tomtom.ecommerce.beans.Product;
import com.tomtom.ecommerce.beans.ResponsePayload;
import com.tomtom.ecommerce.beans.SuccessPayload;
import com.tomtom.ecommerce.exceptions.InvalidUserDetailFoundException;
import com.tomtom.ecommerce.exceptions.OperationFailureException;
import com.tomtom.ecommerce.repositories.impl.CustomerProductRepositoryManagerImpl;
import com.tomtom.ecommerce.service.CustomerCartServiceProvider;

@Service
public class CustomerCartServiceProviderImpl implements CustomerCartServiceProvider {
	private static final Logger LOGGER = LogManager.getLogger(CustomerCartServiceProviderImpl.class);

	@Autowired
	CustomerProductRepositoryManagerImpl customerProductRepositoryManagerImpl;

	@Override
	public ResponseEntity<Cart> getAllProductsFromCart(String userId) throws InvalidUserDetailFoundException {
		LOGGER.info("Fetching products added into the cart for user:" + userId);
		Cart cart = customerProductRepositoryManagerImpl.getAllProductsFromCart(userId);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponsePayload> addProductToCart(String userId, String productId)
			throws InvalidUserDetailFoundException, OperationFailureException, CloneNotSupportedException {
		LOGGER.info("Adding products into the cart for user:" + userId);
		ResponsePayload res = customerProductRepositoryManagerImpl.addProductToCart(userId, productId);
		if (res instanceof SuccessPayload) {
			return new ResponseEntity<ResponsePayload>(res, HttpStatus.OK);
		}
		return new ResponseEntity<ResponsePayload>(res, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<ResponsePayload> removeProductFromCart(String userId, String productId)
			throws InvalidUserDetailFoundException, OperationFailureException, CloneNotSupportedException {
		LOGGER.info("Removing products from the cart for user:" + userId);
		ResponsePayload res = customerProductRepositoryManagerImpl.removeProductFromCart(userId, productId);
		if (res instanceof SuccessPayload) {
			return new ResponseEntity<ResponsePayload>(res, HttpStatus.OK);
		}
		return new ResponseEntity<ResponsePayload>(res, HttpStatus.BAD_REQUEST);
	}

}
