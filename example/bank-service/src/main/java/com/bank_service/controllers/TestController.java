package com.bank_service.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private Environment environment;

	@RequestMapping("/welcome")
	public String getMessage() {
		return "Hello BANK";
	}

	@RequestMapping("/getAvailableBalance")
	public Map<String, Object> getAvailableBalance() {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("Balance", "10000");
		res.put("server", environment.getProperty("local.server.port"));
		return res;
	}
}
