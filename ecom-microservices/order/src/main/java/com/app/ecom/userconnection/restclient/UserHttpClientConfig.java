package com.app.ecom.userconnection.restclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class UserHttpClientConfig {

	@Bean
	public UserHttpClient userHttpClient(RestClient.Builder builder) {
		// rest client
		RestClient restClient = builder.baseUrl("http://ecomuser").build();

		// adapter
		RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);

		// proxy factory
		HttpServiceProxyFactory factory = HttpServiceProxyFactory
				.builderFor(restClientAdapter)
				.build();

		// proxy
		return factory.createClient(UserHttpClient.class);
	}
}
