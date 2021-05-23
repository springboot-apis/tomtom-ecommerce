package com.tomtom.ecommerce.repositories.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomtom.ecommerce.beans.Cart;
import com.tomtom.ecommerce.beans.Customer;
import com.tomtom.ecommerce.beans.ErrorPayload;
import com.tomtom.ecommerce.beans.Product;
import com.tomtom.ecommerce.beans.ResponsePayload;
import com.tomtom.ecommerce.beans.SuccessPayload;
import com.tomtom.ecommerce.exceptions.InvalidUserDetailFoundException;
import com.tomtom.ecommerce.exceptions.OperationFailureException;
import com.tomtom.ecommerce.repositories.CustomerProductRepositoryManager;

@Service
public class CustomerProductRepositoryManagerImpl implements CustomerProductRepositoryManager {
	@Autowired
	DataStoreRepository dataStoreRepository;

	@Override
	public ResponsePayload addProductToCart(String customerId, String productId)
			throws InvalidUserDetailFoundException, OperationFailureException, CloneNotSupportedException {
		// get user from db -> verify user -> add his product to cart -> return
		Optional<Customer> optionalCust = dataStoreRepository.getCustomerById(customerId);
		Customer customer;
		Cart cart = null;
		if (optionalCust.isPresent()) {
			customer = optionalCust.get();
			cart = customer.getCart();
			if (null != cart && null != cart.getProductInCart() && cart.getProductInCart().size() > 0) {
				Optional<Product> opProductInCart = cart.getProductInCart().stream()
						.filter(p -> p.getProductId().equalsIgnoreCase(productId)).findFirst();
				if (opProductInCart.isPresent()) {
					return new ErrorPayload("Product aready present in cart");
				} else {
					cart = customer.getCart();
					Optional<Product> opProductInStore = dataStoreRepository.getProductById(productId);
					cart.getProductInCart().add((Product) opProductInStore.get().clone());
					cart.setTotalBilling(cart.getTotalBilling().add(opProductInStore.get().getProductMrp()));
					return new SuccessPayload("Product added to the cart");
				}

			} else {
				cart = customer.getCart();
				Optional<Product> opProductInStore = dataStoreRepository.getProductById(productId);
				cart.getProductInCart().add((Product) opProductInStore.get().clone());
				cart.setTotalBilling(cart.getTotalBilling().add(opProductInStore.get().getProductMrp()));
				return new SuccessPayload("Product added to the cart");

			}
		} else {
			throw new InvalidUserDetailFoundException("Customer details not found in database");
		}
	}

	@Override
	public ResponsePayload removeProductFromCart(String customerId, String productId)
			throws InvalidUserDetailFoundException {
		// get user from db -> verify user -> remove his product from cart -> return
		Optional<Customer> optionalCust = dataStoreRepository.getCustomerById(customerId);
		Customer customer;
		Cart cart = null;
		if (optionalCust.isPresent()) {
			customer = optionalCust.get();
			cart = customer.getCart();
			if (null != cart && null != cart.getProductInCart() && cart.getProductInCart().size() > 0) {
				Optional<Product> opProduct = cart.getProductInCart().stream()
						.filter(p -> p.getProductId().equalsIgnoreCase(productId)).findFirst();
				if (opProduct.isPresent()) {
					Product productInCart = opProduct.get();
					cart.getProductInCart().remove(productInCart);
					cart.setTotalBilling(cart.getTotalBilling().subtract(opProduct.get().getProductMrp()));
					return new SuccessPayload("Product removed from the cart");
				} else {
					return new ErrorPayload("Product not found in cart");
				}
			} else {
				return new ErrorPayload("Product not found in cart");
			}
		} else {
			throw new InvalidUserDetailFoundException("Customer details not found in database");
		}
	}

	@Override
	public Cart getAllProductsFromCart(String customerId) throws InvalidUserDetailFoundException {
		// get user from db -> verify user -> fetch his cart -> return
		Optional<Customer> optionalCust = dataStoreRepository.getCustomerById(customerId);
		Customer customer;
		Cart cart = null;
		if (optionalCust.isPresent()) {
			customer = optionalCust.get();
			cart = customer.getCart();
		} else {
			throw new InvalidUserDetailFoundException("Customer details not found in database");
		}
		return cart;
	}
}