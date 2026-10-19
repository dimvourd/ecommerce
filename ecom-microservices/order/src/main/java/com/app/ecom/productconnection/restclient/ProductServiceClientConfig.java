package com.app.ecom.productconnection.restclient;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Optional;

@Configuration
public class ProductServiceClientConfig {

	@Bean
	@LoadBalanced
	public RestClient.Builder restClientBuilder() {
		return RestClient.builder();
	}

	@Bean
	public ProductHttpClient productHttpClient(@LoadBalanced RestClient.Builder clientBuilder) {

		RestClient restClient = clientBuilder.baseUrl("http://ecomproduct")
				.defaultStatusHandler( HttpStatusCode::is5xxServerError, (request, response) -> Optional.of(HttpStatus.SERVICE_UNAVAILABLE))
				.build();
//		Adapter
		RestClientAdapter adapter = RestClientAdapter.create(restClient);
//      Factory
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
//		Proxy
		ProductHttpClient productClientProxy = factory.createClient(ProductHttpClient.class);
//		Client interface
		return productClientProxy;
	}
}
