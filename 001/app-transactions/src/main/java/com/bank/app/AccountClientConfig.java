package com.bank.app;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class AccountClientConfig {

	@Bean
	@LoadBalanced
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}

	@Bean
	public AccountClient accountClient(@LoadBalanced WebClient.Builder builder) {
		WebClient webClient = builder.baseUrl("http://app-account").build();

		WebClientAdapter adapter = WebClientAdapter.create(webClient);

		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder()
				.exchangeAdapter(adapter)
				.build();
		AccountClient accountClient = factory.createClient(AccountClient.class);

		return accountClient;
	}
}
