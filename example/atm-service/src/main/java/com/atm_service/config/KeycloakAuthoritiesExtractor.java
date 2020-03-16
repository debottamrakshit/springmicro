package com.atm_service.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

public class KeycloakAuthoritiesExtractor extends JwtAuthenticationConverter {
	public final static String CLIENT_ID_KEY = "azp";
	public final static String RESOURCE_ACCES_KEY = "resource_access";
	public final static String ROLES_KEY = "roles";

	@Override
	protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
		Map<String, Object> tokenData = jwt.getClaims();
		Object resourceAccessMap = tokenData.get(RESOURCE_ACCES_KEY);
		if (resourceAccessMap != null && resourceAccessMap instanceof Map) {
			Object clientDataMap = ((Map<String, Object>) resourceAccessMap).get(tokenData.get(CLIENT_ID_KEY));
			if (clientDataMap != null && clientDataMap instanceof Map) {
				Object rolesMap = ((Map<String, Object>) clientDataMap).get(ROLES_KEY);
				if (rolesMap != null && rolesMap instanceof List) {
					return ((List<String>) rolesMap).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
				}
			}
		}
		return super.extractAuthorities(jwt);
	}
}