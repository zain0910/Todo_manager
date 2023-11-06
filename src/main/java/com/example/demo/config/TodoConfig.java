package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class TodoConfig {

	@Value("${url}")
	private String baseurl;

	@Bean
	WebClient getWebClient() {
		return WebClient.builder().baseUrl(baseurl).build();
	}

	@Bean
	RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}