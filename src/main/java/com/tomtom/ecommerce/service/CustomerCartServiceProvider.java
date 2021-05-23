package com.tomtom.ecommerce.service;

import org.springframework.http.ResponseEntity;

import com.tomtom.ecommerce.beans.Cart;
import com.tomtom.ecommerce.beans.ResponsePayload;
import com.tomtom.ecommerce.exceptions.InvalidUserDetailFoundException;
import com.tomtom.ecommerce.exceptions.OperationFailureException;

public interface CustomerCartServiceProvider {

	public ResponseEntity<Cart> getAllProductsFromCart(String userId) throws InvalidUserDetailFoundException;

	public ResponseEntity<ResponsePayload> addProductToCart(String userId, String productId) throws InvalidUserDetailFoundException, OperationFailureException, CloneNotSupportedException;

	public ResponseEntity<ResponsePayload> removeProductFromCart(String userId, String productId) throws InvalidUserDetailFoundException, OperationFailureException, CloneNotSupportedException;

}
