package com.tomtom.ecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.tomtom.ecommerce.beans.Product;
import com.tomtom.ecommerce.service.impl.CommonProductServiceProviderImpl;

@RestController
public class CommonRequestController {
	@Autowired
	CommonProductServiceProviderImpl commonProductServiceProviderImpl;
	
	@GetMapping("/products")
	public ResponseEntity<List<? extends Product>> getProductList(){
		return new ResponseEntity<List<? extends Product>>
		( commonProductServiceProviderImpl.listAllProducts(), HttpStatus.OK);
	}
}
