package com.bank.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private final AccountClientService accountClientService;
	@Autowired
	public TransactionController(AccountClientService accountClientService) {
		this.accountClientService = accountClientService;
	}

	@PostMapping("/process")
	public String processTransaction(@RequestParam String accountId, @RequestParam Double amount) {
		// 1. calls the account for the balance

		return accountClientService.processTransaction(accountId, amount);
	}
}
