package com.app.client;


import java.util.logging.Logger;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.dto.ProductDTO;


@FeignClient(name = "PRODUCTSERVICE"
,
fallback = ProductServiceClient.ProductServiceClientFallback.class
)
public interface ProductServiceClient {
	
     Logger logger = Logger.getLogger(ProductServiceClient.class
            .getName());
	

	@RequestMapping(method = RequestMethod.GET, value = "/products/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ProductDTO getProduct(@PathVariable("id") String id);

/**
 * Circuit Breaker code, This will run if Invoice-service is down
 * @author hitjoshi
 *
 */	
	@Component
	static class ProductServiceClientFallback implements ProductServiceClient{
		@Override	
		public ProductDTO getProduct(String id) {
			ProductDTO productDTO = new ProductDTO();
			logger.info("Service is down, please try later");
			return productDTO;
		}
	}
}
