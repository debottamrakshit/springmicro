package com.atm_service.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RandomRule;

@RibbonClient(name = "bank-service")
public class RibbonConfiguration {

	Logger log = LoggerFactory.getLogger(RibbonConfiguration.class);

	@Bean
	public IRule loadBlancingRule() {
		return new RandomRule();
	}

	@Bean
	public IPing pingConfiguration() {

		String pingPath = "/actuator/health";
		IPing ping = new PingUrl(false, pingPath);
		return ping;

	}

}
