package com.app.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.OrderDTO;
import com.app.model.Order;
import com.app.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Optional<Order> getOrder(long id) {
		return orderRepository.findById(id);
	}
	
	public Order saveOrder(OrderDTO product) {
		
		//call service and feign client here
		//return orderRepository.save(product);
		return null;
	}

	
}
