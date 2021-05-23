package com.tomtom.ecommerce.repositories;

import com.tomtom.ecommerce.beans.Product;
import com.tomtom.ecommerce.beans.ResponsePayload;
import com.tomtom.ecommerce.exceptions.InvalidUserDetailFoundException;
import com.tomtom.ecommerce.exceptions.OperationFailureException;

public interface SellerProductRepositoryManager {
	public ResponsePayload addProductToStore(String sellerId, Product product) throws InvalidUserDetailFoundException, OperationFailureException, CloneNotSupportedException;
}