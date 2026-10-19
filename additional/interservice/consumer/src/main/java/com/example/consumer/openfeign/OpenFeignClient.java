package com.example.consumer.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name ="provider-service", url = "http://localhost:7979")
public interface OpenFeignClient {

	@GetMapping("/instance-info")
	String getInstanceInfo();
}
