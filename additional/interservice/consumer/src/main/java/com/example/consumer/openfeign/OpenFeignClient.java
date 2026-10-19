package com.example.consumer.openfeign;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name ="provider")
public interface OpenFeignClient {

	@GetMapping("/instance-info")
//	@LoadBalanced its not needed in open feign
	String getInstanceInfo();
}
