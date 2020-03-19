package com.bank_service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank_service.domain.BankDetails;
import com.bank_service.exception.BankDetailsException;
import com.bank_service.repository.BankDetailsRepository;
import com.bank_service.service.BankDetailsService;

@Service
public class BankDetailsServiceImpl implements BankDetailsService {

	@Autowired
	BankDetailsRepository bankDetailsRepository;

	@Override
	public Optional<BankDetails> getBankDetails(String id) {
		return bankDetailsRepository.findById(id);
	}

	@Override
	public List<BankDetails> getAllBankDetails() {
		return bankDetailsRepository.findAll();
	}

	@Override
	public void deleteBankDetails(String id) throws BankDetailsException {
		if (bankDetailsRepository.existsById(id)) {
			bankDetailsRepository.deleteById(id);
		} else {
			throw new BankDetailsException("Record not found");
		}
	}

	@Override
	public void updateBankDetails(BankDetails bankDetails) throws BankDetailsException {
		if (bankDetailsRepository.existsById(bankDetails.getId())) {
			bankDetailsRepository.save(bankDetails);
		} else {
			throw new BankDetailsException("Record not found");
		}
	}

	@Override
	public void saveBankDetails(BankDetails bankDetails) throws BankDetailsException{

		if (bankDetailsRepository.existsById(bankDetails.getId())) {
			throw new BankDetailsException("Record already exist");
		} else {
			bankDetailsRepository.save(bankDetails);
		}
	}

}
