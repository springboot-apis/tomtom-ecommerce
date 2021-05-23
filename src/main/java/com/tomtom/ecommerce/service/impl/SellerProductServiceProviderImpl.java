package com.tomtom.ecommerce.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tomtom.ecommerce.beans.Cart;
import com.tomtom.ecommerce.beans.Product;
import com.tomtom.ecommerce.beans.ResponsePayload;
import com.tomtom.ecommerce.exceptions.InvalidUserDetailFoundException;
import com.tomtom.ecommerce.exceptions.OperationFailureException;
import com.tomtom.ecommerce.repositories.impl.SellerProductRepositoryManagerImpl;
import com.tomtom.ecommerce.service.SellerProductServiceProvider;

@Service
public class SellerProductServiceProviderImpl implements SellerProductServiceProvider {
	private static final Logger LOGGER = LogManager.getLogger(SellerProductServiceProviderImpl.class);
@Autowired
SellerProductRepositoryManagerImpl sellerProductRepositoryManagerImpl;

	@Override
	public ResponseEntity<ResponsePayload>  addProductToStock(String userId, Product product) throws InvalidUserDetailFoundException, OperationFailureException, CloneNotSupportedException {
		LOGGER.info("Adding products  into the datastore for seller:" + userId);
		ResponsePayload res = sellerProductRepositoryManagerImpl.addProductToStore(userId, product);
		return new ResponseEntity<ResponsePayload>(res, HttpStatus.OK);

	}

}
