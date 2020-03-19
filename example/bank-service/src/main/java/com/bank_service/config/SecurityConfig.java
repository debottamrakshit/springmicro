package com.bank_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable()
				.authorizeRequests().antMatchers("/", "/login**").permitAll().antMatchers("/bank/getAvailableBalance")
				.hasRole("USER").antMatchers("/bank/welcome").hasAnyRole("ADMIN", "USER").and().oauth2ResourceServer()
				.jwt().jwtAuthenticationConverter(new KeycloakAuthoritiesExtractor());

	}
}