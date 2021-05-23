package com.tomtom.ecommerce.service;

import org.springframework.http.ResponseEntity;

import com.tomtom.ecommerce.beans.Product;
import com.tomtom.ecommerce.beans.ResponsePayload;
import com.tomtom.ecommerce.exceptions.InvalidUserDetailFoundException;
import com.tomtom.ecommerce.exceptions.OperationFailureException;

public interface SellerProductServiceProvider {

	public ResponseEntity<ResponsePayload> addProductToStock(String userId, Product product) throws InvalidUserDetailFoundException, OperationFailureException, CloneNotSupportedException;
}
