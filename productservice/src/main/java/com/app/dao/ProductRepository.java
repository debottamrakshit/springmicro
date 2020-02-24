package com.app.dao;

import org.springframework.data.repository.CrudRepository;
import com.app.model.Product;


public interface ProductRepository extends CrudRepository<Product, Integer> {

}
