package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.client.ProductServiceClient;
import com.app.dto.OrderDTO;
import com.app.dto.ProductDTO;
import com.app.exception.ResourceNotFoundException;
import com.app.model.Order;
import com.app.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "Product Management System", description = "Operations pertaining to product in Product Management System")
public class OrderController {

	@Autowired
	private OrderService orderService;

	
	@Autowired
	ProductServiceClient productServiceClient;



	@ApiOperation(value = "Get a Order by Id")
	@GetMapping("/orders/{id}")
	public ResponseEntity<ProductDTO> getOrder(@ApiParam(value = "Retrieve Order by order id", required = true) @PathVariable long id) throws ResourceNotFoundException {
		//Order product = orderService.getOrder(id).orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + id));
		ProductDTO product2 = productServiceClient.getProduct("1");
		return ResponseEntity.ok().body(product2);
	}

	@ApiOperation(value = "Add an product")
	@PostMapping("/orders")
	public Order saveProduct(@ApiParam(value = "Order object store in database table", required = true) @RequestBody @Valid OrderDTO order) {
		return orderService.saveOrder(order);
	}
	
	
}
