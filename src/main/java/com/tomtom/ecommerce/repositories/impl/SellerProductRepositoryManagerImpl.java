package com.tomtom.ecommerce.repositories.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomtom.ecommerce.beans.ErrorPayload;
import com.tomtom.ecommerce.beans.Product;
import com.tomtom.ecommerce.beans.ResponsePayload;
import com.tomtom.ecommerce.beans.Seller;
import com.tomtom.ecommerce.exceptions.InvalidUserDetailFoundException;
import com.tomtom.ecommerce.exceptions.OperationFailureException;
import com.tomtom.ecommerce.repositories.SellerProductRepositoryManager;

@Service
public class SellerProductRepositoryManagerImpl implements SellerProductRepositoryManager {
	@Autowired
	DataStoreRepository dataStoreRepository;

	@Override
	public ResponsePayload addProductToStore(String sellerId, Product product)
			throws InvalidUserDetailFoundException, OperationFailureException, CloneNotSupportedException {
		// get user from db -> verify user -> add his product to datastore -> return
		Optional<Seller> optionalSeller = dataStoreRepository.getSellerById(sellerId);
		Seller seller;
		if (optionalSeller.isPresent()) {
			seller = optionalSeller.get();
			ResponsePayload res = dataStoreRepository.addProductToDb(product);
			return res;
		} else {
			throw new InvalidUserDetailFoundException("Seller details not found in database");
		}

	}
}