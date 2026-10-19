package com.bank.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private final Map<String, Double> accounts;

	public AccountController() {
		accounts = new HashMap<>();
		accounts.put("A100",5000.0);
		accounts.put("A200", 1000.0);
		accounts.put("A300", 0.0);
	}

	@GetMapping("/{accountId}/balance")
	public Double getBalance(@PathVariable String accountId) {
		//8080
		return accounts.get(accountId);
	}
}
