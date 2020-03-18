package com.gateway.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
public class SwaggerDocumentationConfig implements SwaggerResourcesProvider {

	@Autowired
	private DiscoveryClient discoveryClient;
	Logger log = LoggerFactory.getLogger(SwaggerDocumentationConfig.class);
	private static final String DEFAULT_SWAGGER_URL = "/v2/api-docs";
	private static final String KEY_SWAGGER_URL = "swagger_url";
	@Autowired
	private ZuulProperties zuulProperties;
	@Autowired
	private ZuulHandlerMapping zuulHandlerMapping;

	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
		discoveryClient.getServices().stream().forEach(serviceId -> {
			log.info("Attempting service definition refresh for Service : {} ", serviceId);
			List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
			if (serviceInstances == null || serviceInstances.isEmpty()) {
				log.info("No instances available for service : {} ", serviceId);
			} else {
				ServiceInstance instance = serviceInstances.get(0);
				log.info("Swagger URL : {} ", getSwaggerURL(instance, serviceId));
				resources.add(swaggerResource(serviceId, getSwaggerURL(instance, serviceId), "2.0"));

				zuulProperties.getRoutes().put(serviceId + "-docs", new ZuulRoute(serviceId + "-docs",
						"/" + serviceId + "/**", serviceId, null, true, false, new HashSet<>()));
				zuulHandlerMapping.setDirty(true);
			}
		});
		return resources;
	}

	private String getSwaggerURL(ServiceInstance instance, String serviceId) {
		String swaggerURL = instance.getMetadata().get(KEY_SWAGGER_URL);
		return "/" + serviceId + (swaggerURL != null ? swaggerURL : DEFAULT_SWAGGER_URL);
	}

	private SwaggerResource swaggerResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}
}
