package com.atm_service.service;

import java.util.List;
import java.util.Optional;

import com.atm_service.domain.AtmDetails;
import com.atm_service.exception.AtmDetailsException;

public interface AtmDetailsService {

	Optional<AtmDetails> getAtmDetails(String id);

	List<AtmDetails> getAllAtmDetails();

	void deleteAtmDetails(String id) throws AtmDetailsException;

	void updateAtmDetails(AtmDetails atmDetails) throws AtmDetailsException;

	void saveAtmDetails(AtmDetails atmDetails) throws AtmDetailsException;

}
