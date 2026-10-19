package com.example.consumer.restclient;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rest-client")
@RequiredArgsConstructor
public class RestClientController {

	private final ProviderRestClient providerRestClient;

	@GetMapping("/instance")
	public String getInstance(){
		String response = providerRestClient.getInstanceInfo();

		return response;
	}
}
