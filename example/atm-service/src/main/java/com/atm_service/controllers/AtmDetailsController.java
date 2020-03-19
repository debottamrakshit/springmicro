package com.atm_service.controllers;

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

import com.atm_service.domain.AtmDetails;
import com.atm_service.exception.AtmDetailsException;
import com.atm_service.service.AtmDetailsService;

@Controller
@RequestMapping("/atm")
public class AtmDetailsController {

	private final Logger log = LoggerFactory.getLogger(AtmDetailsController.class);

	@Autowired
	AtmDetailsService atmDetailsService;

	@RequestMapping(value = "/atmDetails", method = RequestMethod.GET)
	public ResponseEntity<?> atmDetails() {
		try {
			List<AtmDetails> atmDetailsList = atmDetailsService.getAllAtmDetails();
			return new ResponseEntity<List<AtmDetails>>(atmDetailsList, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error occured : {}", e);
			return new ResponseEntity<String>("Error while fetching the data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/atmDetails/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> atmDetails(@PathVariable String id) {
		try {
			Optional<AtmDetails> atmDetails = atmDetailsService.getAtmDetails(id);
			if (atmDetails.isPresent()) {
				return new ResponseEntity<AtmDetails>(atmDetails.get(), HttpStatus.OK);
			}
			return new ResponseEntity<String>("Record not found", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			log.error("Error occured : {}", e);
			return new ResponseEntity<String>("Error while fetching the data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/atmDetails/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAtmDetails(@PathVariable String id) {
		try {
			atmDetailsService.deleteAtmDetails(id);
			return new ResponseEntity<String>("Data deleted sucessfully", HttpStatus.OK);
		} catch (AtmDetailsException e) {
			log.error("Error occured : {}", e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error occured : {}", e);
			return new ResponseEntity<String>("Error while deleting the data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/atmDetails", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAtmDetails(@RequestBody AtmDetails atmDetails) {
		try {
			atmDetailsService.updateAtmDetails(atmDetails);
			return new ResponseEntity<String>("Data updated successfully", HttpStatus.OK);
		} catch (AtmDetailsException e) {
			log.error("Error occured : {}", e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error occured : {}", e);
			return new ResponseEntity<String>("Error while updating the data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/atmDetails", method = RequestMethod.POST)
	public ResponseEntity<?> saveAtmDetails(@RequestBody AtmDetails atmDetails) {
		try {
			atmDetailsService.saveAtmDetails(atmDetails);
			return new ResponseEntity<String>("Data saved successfully", HttpStatus.OK);
		} catch (AtmDetailsException e) {
			log.error("Error occured : {}", e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Error occured : {}", e);
			return new ResponseEntity<String>("Error while saving the data", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
