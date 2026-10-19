package com.example.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstanceController {

	@GetMapping("/instance-info")
	public String getInstance(){
		return "Hello World";
	}
}
