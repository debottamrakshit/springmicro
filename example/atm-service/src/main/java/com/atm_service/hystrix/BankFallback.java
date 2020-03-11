package com.atm_service.hystrix;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.atm_service.client.BankClient;

@Component
public class BankFallback implements BankClient {

	@Override
	public Map<String, Object> getAvailableBalance() {
		HashMap<String, Object> out = new HashMap<>();
		out.put("Message", "Service is Down");
		return out;
	}

}
