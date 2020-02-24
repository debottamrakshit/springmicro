package com.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.model.Order;


public interface OrderRepository extends CrudRepository<Order, Long> {

}
