package com.example.consumer.restclient;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

//	@Bean
//	public RestClient restClient() {
//		return RestClient.builder()
//				.baseUrl("http://localhost:7979")
//				.build();
//	}
//
	@Bean
	@Primary
	public RestClient.Builder normalRestClientBuilder() {
		return RestClient.builder();
	}

	@Bean
	@LoadBalanced
	public RestClient.Builder loadBalancedRestClientBuilder() {
		return RestClient.builder();
	}

	@Bean
	public RestClient restClient(@LoadBalanced RestClient.Builder builder) {

		return builder
				.baseUrl("http://provider")
				.build();
	}
}
