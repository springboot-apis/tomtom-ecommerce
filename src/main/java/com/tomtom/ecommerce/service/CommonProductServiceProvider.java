package com.tomtom.ecommerce.service;

import java.util.List;

import com.tomtom.ecommerce.beans.Product;

public interface CommonProductServiceProvider {

	public List<? extends Product> listAllProducts();
}
