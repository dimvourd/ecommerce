package com.example.consumer.openfeign;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/open-feign")
public class OpenFeignController {

	private final OpenFeignClient openFeignClient;

	@GetMapping("/instanceAnswer")
	public String getInstanceAnswer() {
		return openFeignClient.getInstanceInfo();
	}
}
