package com.tomtom.ecommerce.service;

import com.tomtom.ecommerce.beans.ResponsePayload;

public interface CustomerCheckoutServiceProvider {
	public ResponsePayload makePayment(String userId, String productId);
}
