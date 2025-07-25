package com.cmr.affilieprojet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/**", "/h2-console/**", "/actuator/**").permitAll()
	            .anyRequest().permitAll()
	        )
	        .csrf(csrf -> csrf
	            .ignoringRequestMatchers("/api/**", "/h2-console/**", "/actuator/**")
	            .disable()
	        )
	        .headers(headers -> headers
	            .frameOptions(frame -> frame.sameOrigin())  
	        );

	    return http.build();
	}

}