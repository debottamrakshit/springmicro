package com.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ProductRepository;
import com.app.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Iterable<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
}
