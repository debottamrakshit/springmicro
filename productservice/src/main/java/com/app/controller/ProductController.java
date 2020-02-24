package com.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Product;
import com.app.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/products")
	public Iterable<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable long id) {
		Optional<Product> product = productService.getProduct(id);
		if(product.isPresent()){
			return product.get();
		}else{
			throw new RuntimeException("Product does not exist");
		}
	}
	
	@PostMapping("/product")
	public Product saveProduct(@RequestBody Product product) {
		Product savedProduct = productService.saveProduct(product);
		if(savedProduct == null){
			throw new RuntimeException("Unable to create Product");
		}else{
			return savedProduct;
		}
	}
}
