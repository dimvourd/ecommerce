package com.example.consumer.httpinterface;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/http-interface")
@RequiredArgsConstructor
public class HttpInterfaceController {

	private final ProviderHttpInterfaceClient providerHttpInterfaceClient;

	@GetMapping("/instanceAnswer")
	public String getInstanceInfo() {
		String response = providerHttpInterfaceClient.getInstanceInfo();
		return response;
	}
}
