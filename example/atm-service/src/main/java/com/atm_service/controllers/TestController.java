package com.atm_service.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atm_service.client.BankClient;

@RestController
public class TestController {
	@Autowired
	BankClient bankClient;

	@RequestMapping("/welcome")
	public String getMessage() {
		return "Hello ATM";
	}

	@RequestMapping("/getAvailableBalance")
	public Map<String, Object> getAvailableBalance() {
		return bankClient.getAvailableBalance();
	}

}
