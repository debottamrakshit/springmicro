package com.atm_service.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atm_service.client.BankClient;

@RestController
public class TestController {
	
	final Logger log = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	BankClient bankClient;

	@RequestMapping("/welcome")
	public String getMessage() {
		return "Hello ATM";
	}

	@RequestMapping("/getAvailableBalance")
	public Map<String, Object> getAvailableBalance() {
		log.info("AtmService - getAvailableBalance CALLED");
		return bankClient.getAvailableBalance();
	}

}
