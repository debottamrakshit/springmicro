package com.bank_service.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bank_service.domain.BankDetails;
import com.bank_service.exception.BankDetailsException;
import com.bank_service.service.BankDetailsService;

@Controller
@RequestMapping("/bank")
public class BankDetailsController {

	private final Logger log = LoggerFactory.getLogger(BankDetailsController.class);

	@Autowired
	BankDetailsService bankDetailsService;

	@RequestMapping(value = "/bankDetails", method = RequestMethod.GET)
	public ResponseEntity<?> bankDetails() {
		try {
			List<BankDetails> bankDetailsList = bankDetailsService.getAllBankDetails();
			return new ResponseEntity<List<BankDetails>>(bankDetailsList, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error occured : {}", e);
			return new ResponseEntity<String>("Error while fetching the data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/bankDetails/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> bankDetails(@PathVariable String id) {
		try {
			Optional<BankDetails> bankDetails = bankDetailsService.getBankDetails(id);
			if (bankDetails.isPresent()) {
				return new ResponseEntity<BankDetails>(bankDetails.get(), HttpStatus.OK);
			}
			return new ResponseEntity<String>("Record not found", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			log.error("Error occured : {}", e);
			return new ResponseEntity<String>("Error while fetching the data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/bankDetails/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBankDetails(@PathVariable String id) {
		try {
			bankDetailsService.deleteBankDetails(id);
			return new ResponseEntity<String>("Data deleted sucessfully", HttpStatus.OK);
		} catch (BankDetailsException e) {
			log.error("Error occured : {}", e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error occured : {}", e);
			return new ResponseEntity<String>("Error while deleting the data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/bankDetails", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBankDetails(@RequestBody BankDetails bankDetails) {
		try {
			bankDetailsService.updateBankDetails(bankDetails);
			return new ResponseEntity<String>("Data updated successfully", HttpStatus.OK);
		} catch (BankDetailsException e) {
			log.error("Error occured : {}", e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error occured : {}", e);
			return new ResponseEntity<String>("Error while updating the data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/bankDetails", method = RequestMethod.POST)
	public ResponseEntity<?> saveBankDetails(@RequestBody BankDetails bankDetails) {
		try {
			bankDetailsService.saveBankDetails(bankDetails);
			return new ResponseEntity<String>("Data saved successfully", HttpStatus.OK);
		} catch (BankDetailsException e) {
			log.error("Error occured : {}", e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error occured : {}", e);
			return new ResponseEntity<String>("Error while saving the data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
