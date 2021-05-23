package com.tomtom.ecommerce.repositories.impl;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tomtom.ecommerce.beans.Cart;
import com.tomtom.ecommerce.beans.Customer;
import com.tomtom.ecommerce.beans.Product;
import com.tomtom.ecommerce.beans.ResponsePayload;
import com.tomtom.ecommerce.beans.Seller;
import com.tomtom.ecommerce.beans.SuccessPayload;
import com.tomtom.ecommerce.exceptions.OperationFailureException;

import lombok.Getter;

@Component
@Getter

public class DataStoreRepository {
	private static final Logger LOGGER = LogManager.getLogger(DataStoreRepository.class);
	private static List<Product> allProductsLocalCache = new LinkedList<Product>();
	private static List<Customer> allCustomersLocalCache = new LinkedList<Customer>();
	private static List<Seller> allSellersLocalCache = new LinkedList<Seller>();

	public DataStoreRepository() {
		super();
		loadProductFromDb();
		loadCustomersFromDb();
		loadSellersFromDb();
	}

	private static final void loadCustomersFromDb() {
		allCustomersLocalCache.add(new Customer("111", "palash", new Cart(new LinkedList<>(), new BigDecimal(0))));
		allCustomersLocalCache.add(new Customer("222", "Aman", new Cart(new LinkedList<>(), new BigDecimal(0))));
	}

	private static final void loadSellersFromDb() {
		allSellersLocalCache.add(new Seller("999", "aws"));
	}

	private static final void loadProductFromDb() {
		allProductsLocalCache.add(new Product("99", "Lenovo yoga Laptop 15inch", new BigDecimal(40000)));
		allProductsLocalCache.add(new Product("100", "MacBook air Laptop 13inch", new BigDecimal(100000)));
	}

	public final static List<? extends Product> getAllProductsFromStore() {
		return allProductsLocalCache;
	}

	public Optional<Customer> getCustomerById(String userId) {
		return allCustomersLocalCache.stream().filter(c -> c.getCustomerId().equalsIgnoreCase(userId)).findFirst();
	}

	public Optional<Product> getProductById(String productId) {
		return allProductsLocalCache.stream().filter(p -> p.getProductId().equalsIgnoreCase(productId)).findFirst();
	}

	public Optional<Seller> getSellerById(String sellerId) {
		return allSellersLocalCache.stream().filter(s -> s.getSellerId().equalsIgnoreCase(sellerId)).findFirst();
	}


	public ResponsePayload addProductToDb(Product productInRequest) throws OperationFailureException {
		try {

			Optional<Product> optionalProduct = allProductsLocalCache.stream()
					.filter(p -> p.getProductId().equalsIgnoreCase(productInRequest.getProductId())).findFirst();
			Product product = null;
			if (optionalProduct.isPresent()) {
				return new SuccessPayload("Product already present");
			} else {
				allProductsLocalCache.add(productInRequest);
				LOGGER.info("Product added to database");
				return new SuccessPayload("Product added to database");
			}

		} catch (Exception e) {
			throw new OperationFailureException(e.getMessage());
		}
	}

}
