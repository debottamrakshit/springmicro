package com.atm_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atm_service.domain.AtmDetails;

@Repository
public interface AtmDetailsRepository extends MongoRepository<AtmDetails, String> {

}
