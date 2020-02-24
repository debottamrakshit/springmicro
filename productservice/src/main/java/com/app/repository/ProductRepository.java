package com.app.repository;

import org.springframework.data.repository.CrudRepository;
import com.app.model.Product;


public interface ProductRepository extends CrudRepository<Product, Long> {

}
