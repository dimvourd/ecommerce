package com.example.consumer.httpinterface;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class HttpInterfaceConfig {

//	@Bean
//	public ProviderHttpInterfaceClient restClientHttpInterfaceClient() {
//		// Initialize HTTP client
//		RestClient restClient = RestClient.create("http://localhost:7979");
//
//		// Create factory for client proxies
//		HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builder()
//				.exchangeAdapter(RestClientAdapter.create(restClient))
//				.build();
//
//		// Create client proxy
//		ProviderHttpInterfaceClient client = proxyFactory.createClient(ProviderHttpInterfaceClient.class);
//
//		return client;
//	}

	@Bean
	public ProviderHttpInterfaceClient webClientHttpInterfaceClient(@LoadBalanced WebClient.Builder builder) {
		// Initialize HTTP client
//		WebClient webClient = WebClient.create("http://localhost:7979");

		WebClient webClient = builder.baseUrl("http://provider").build();

		// Create client adapter
		WebClientAdapter webClientAdapter = WebClientAdapter.create(webClient);

		// Create factory for client proxies
		HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builder()
				.exchangeAdapter(webClientAdapter)
				.build();

		// Create client proxy
		ProviderHttpInterfaceClient client = proxyFactory.createClient(ProviderHttpInterfaceClient.class);

		return client;
	}

}
