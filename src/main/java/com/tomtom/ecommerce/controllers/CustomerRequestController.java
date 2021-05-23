package com.tomtom.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomtom.ecommerce.beans.Cart;
import com.tomtom.ecommerce.beans.Product;
import com.tomtom.ecommerce.beans.ResponsePayload;
import com.tomtom.ecommerce.exceptions.InvalidUserDetailFoundException;
import com.tomtom.ecommerce.exceptions.JwtAuthorizationFailedException;
import com.tomtom.ecommerce.exceptions.OperationFailureException;
import com.tomtom.ecommerce.service.CustomerCartServiceProvider;
import com.tomtom.ecommerce.service.impl.CustomerCartServiceProviderImpl;
import com.tomtom.ecommerce.service.impl.CustomerCheckoutServiceProviderImpl;

@RestController
@RequestMapping("/customer")
public class CustomerRequestController {
	@Autowired
	CustomerCartServiceProviderImpl customerCartServiceProviderImpl;

	@Autowired
	CustomerCheckoutServiceProviderImpl customerCheckoutServiceProviderImpl;

	@GetMapping("/cart/products")
	public ResponseEntity<Cart> getProductListFromCart(Authentication auth) throws InvalidUserDetailFoundException {
		return customerCartServiceProviderImpl.getAllProductsFromCart(auth.getName());
	}

	@PutMapping("/cart/add-product")
	public ResponseEntity<ResponsePayload> addProductToCart(Authentication auth, @RequestBody Product product)
			throws InvalidUserDetailFoundException, OperationFailureException, CloneNotSupportedException {
		return customerCartServiceProviderImpl.addProductToCart(auth.getName(), product.getProductId());
	}

	@PutMapping("/cart/remove-product")
	public ResponseEntity<ResponsePayload> removeProductFromCart(Authentication auth, @RequestBody Product product)
			throws InvalidUserDetailFoundException, OperationFailureException, CloneNotSupportedException {
		return customerCartServiceProviderImpl.removeProductFromCart(auth.getName(), product.getProductId());
	}

	@PostMapping("/checkout")
	public ResponseEntity<String> checkout(Authentication auth) throws InvalidUserDetailFoundException, JwtAuthorizationFailedException{
		return customerCheckoutServiceProviderImpl.checkout(auth.getName());
	}

	@PostMapping("/payment")
	public ResponseEntity<ResponsePayload> makePayment(Authentication auth, @RequestBody String jwtData) throws JwtAuthorizationFailedException{
		return customerCheckoutServiceProviderImpl.makePayment(auth.getName(), jwtData);
	}
}
