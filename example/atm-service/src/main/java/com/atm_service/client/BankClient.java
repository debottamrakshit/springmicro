package com.atm_service.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atm_service.hystrix.BankFallback;

@FeignClient(name = "bank-service",fallback = BankFallback.class)
public interface BankClient {
	@RequestMapping("/getAvailableBalance")
	public Map<String, Object> getAvailableBalance();
}
