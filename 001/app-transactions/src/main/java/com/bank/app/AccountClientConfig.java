package com.bank.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class AccountClientConfig {

	@Bean
	public AccountClient accountClient() {
		WebClient webClient = WebClient.create("http://localhost:8080");

		WebClientAdapter adapter = WebClientAdapter.create(webClient);

		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder()
				.exchangeAdapter(adapter)
				.build();
		AccountClient accountClient = factory.createClient(AccountClient.class);

		return accountClient;
	}
}
