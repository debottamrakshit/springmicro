package com.atm_service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atm_service.domain.AtmDetails;
import com.atm_service.exception.AtmDetailsException;
import com.atm_service.repository.AtmDetailsRepository;
import com.atm_service.service.AtmDetailsService;

@Service
public class AtmDetailsServiceImpl implements AtmDetailsService {

	@Autowired
	AtmDetailsRepository atmDetailsRepository;

	@Override
	public Optional<AtmDetails> getAtmDetails(String id) {
		return atmDetailsRepository.findById(id);
	}

	@Override
	public List<AtmDetails> getAllAtmDetails() {
		return atmDetailsRepository.findAll();
	}

	@Override
	public void deleteAtmDetails(String id) throws AtmDetailsException {
		if (atmDetailsRepository.existsById(id)) {
			atmDetailsRepository.deleteById(id);
		} else {
			throw new AtmDetailsException("Record not found");
		}
	}

	@Override
	public void updateAtmDetails(AtmDetails atmDetails) throws AtmDetailsException {
		if (atmDetailsRepository.existsById(atmDetails.getId())) {
			atmDetailsRepository.save(atmDetails);
		} else {
			throw new AtmDetailsException("Record not found");
		}
	}

	@Override
	public void saveAtmDetails(AtmDetails atmDetails) throws AtmDetailsException{

		if (atmDetailsRepository.existsById(atmDetails.getId())) {
			throw new AtmDetailsException("Record already exist");
		} else {
			atmDetailsRepository.save(atmDetails);
		}
	}

}
