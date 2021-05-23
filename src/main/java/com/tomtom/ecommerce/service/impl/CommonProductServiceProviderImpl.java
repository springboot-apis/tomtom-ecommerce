package com.tomtom.ecommerce.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tomtom.ecommerce.beans.Product;
import com.tomtom.ecommerce.repositories.impl.DataStoreRepository;
import com.tomtom.ecommerce.service.CommonProductServiceProvider;


@Service
public class CommonProductServiceProviderImpl implements CommonProductServiceProvider{
	private static final Logger LOGGER = LogManager.getLogger(CommonProductServiceProviderImpl.class);
	
	@Override
	public List<? extends Product> listAllProducts() {
		LOGGER.info("Fetching all products from store");
		return DataStoreRepository.getAllProductsFromStore();
	}

}
