package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Product;
import com.app.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/products")
	public Iterable<Product> retrieveCoursesForStudent() {
		return productService.getAllProducts();
	}
}
