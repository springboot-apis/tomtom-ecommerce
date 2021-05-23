package com.tomtom.ecommerce.repositories;

import com.tomtom.ecommerce.beans.Cart;
import com.tomtom.ecommerce.beans.ResponsePayload;
import com.tomtom.ecommerce.exceptions.InvalidUserDetailFoundException;
import com.tomtom.ecommerce.exceptions.OperationFailureException;

public interface CustomerProductRepositoryManager {
	public ResponsePayload addProductToCart(String customerId,String productId) throws InvalidUserDetailFoundException, OperationFailureException, CloneNotSupportedException;

	public ResponsePayload removeProductFromCart(String customerId, String productId)
			throws InvalidUserDetailFoundException;
	public Cart getAllProductsFromCart(String customerId) throws InvalidUserDetailFoundException;


}