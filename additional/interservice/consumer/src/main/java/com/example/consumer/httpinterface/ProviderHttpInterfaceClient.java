package com.example.consumer.httpinterface;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface ProviderHttpInterfaceClient {

	@GetExchange("/instance-info")
	String getInstanceInfo();
}
