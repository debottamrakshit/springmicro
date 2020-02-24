package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.ResourceNotFoundException;
import com.app.model.Product;
import com.app.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Product Management System", description = "Operations pertaining to product in Product Management System")
public class ProductController {

	@Autowired
	private ProductService productService;

	@ApiOperation(value = "View a list of available product", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/products")
	public Iterable<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@ApiOperation(value = "Get a product by Id")
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@ApiParam(value = "Retrieve Product by product id", required = true) @PathVariable long id) throws ResourceNotFoundException {
		Product product = productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		return ResponseEntity.ok().body(product);
	}

	@ApiOperation(value = "Add an product")
	@PostMapping("/products")
	public Product saveProduct(@ApiParam(value = "Product object store in database table", required = true) @RequestBody @Valid Product product) {
		return productService.saveProduct(product);
	}
	
	@ApiOperation(value = "Update a product")
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateEmployee(
			@ApiParam(value = "Employee Id to update employee object", required = true)
			@PathVariable(value = "id") Long id,
			@ApiParam(value = "Update employee object", required = true)
			@Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
		Product product = productService.getProduct(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));

		product.setName(productDetails.getName());
		final Product updatedProduct = productService.saveProduct(product);
		return ResponseEntity.ok(updatedProduct);
	}

	@ApiOperation(value = "Delete an employee")
	@DeleteMapping("/products/{id}")
	public Map<String, Boolean> deleteProduct(
			@ApiParam(value = "Delete Product by id", required = true)
			@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Product product = productService.getProduct(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
		productService.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
