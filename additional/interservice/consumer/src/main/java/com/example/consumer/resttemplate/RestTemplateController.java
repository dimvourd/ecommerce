package com.example.consumer.resttemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest-template")
public class RestTemplateController {

	private final RestTemplateClient restTemplateClient;

	@GetMapping("/instanceAnswer")
	public String getInstanceAnswer() {
		String answer = restTemplateClient.getInstanceInfo();
		return answer;
	}
}
