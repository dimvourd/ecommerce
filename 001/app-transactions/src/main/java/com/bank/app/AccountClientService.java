package com.bank.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientException;

@Service
public class AccountClientService {

	private final AccountClient accountClient;

	@Autowired
	public AccountClientService(AccountClient accountClient) {
		this.accountClient = accountClient;
	}

	public String processTransaction(String accountId, Double amount) {
		try {
			Double balance = accountClient.getAccountBalance(accountId);
			if (balance.compareTo(amount) >= 0) {
				// TODO: calls the account to remove the amount (I won't do it)

				return String.format("Transaction Approved: $%.1f debited from account %s", amount, accountId);
			} else {
				return "Transaction Rejected: Insufficient balance in account: " + accountId;
			}
		} catch (WebClientException e) {
			// I used general Exception, but he used WebClientException (which I preferred)
			return "Transaction Failed: AccountService unavailable, please try again later.";
		}
	}
}
