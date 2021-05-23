package com.tomtom.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomtom.ecommerce.beans.Product;
import com.tomtom.ecommerce.beans.ResponsePayload;
import com.tomtom.ecommerce.exceptions.InvalidUserDetailFoundException;
import com.tomtom.ecommerce.exceptions.OperationFailureException;
import com.tomtom.ecommerce.service.impl.SellerProductServiceProviderImpl;

@RestController
@RequestMapping("/seller")
public class SellerRequestController {

	@Autowired
	SellerProductServiceProviderImpl sellerProductServiceProviderImpl;

	@PutMapping("/add-products")
	public ResponseEntity<ResponsePayload> addProductToStore(Authentication auth, @RequestBody Product product)
			throws InvalidUserDetailFoundException, OperationFailureException, CloneNotSupportedException {
		return sellerProductServiceProviderImpl.addProductToStock(auth.getName(), product);
	}

}
