package com.bank_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bank_service.domain.BankDetails;


@Repository
public interface BankDetailsRepository extends MongoRepository<BankDetails, String> {

}
