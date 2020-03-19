package com.bank_service.service;

import java.util.List;
import java.util.Optional;

import com.bank_service.domain.BankDetails;
import com.bank_service.exception.BankDetailsException;

public interface BankDetailsService {

	Optional<BankDetails> getBankDetails(String id);

	List<BankDetails> getAllBankDetails();

	void deleteBankDetails(String id) throws BankDetailsException;

	void updateBankDetails(BankDetails bankDetails) throws BankDetailsException;

	void saveBankDetails(BankDetails bankDetails) throws BankDetailsException;

}
