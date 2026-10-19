package com.bank.app;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

// i did not use @Component like the teacher of the course.
@HttpExchange("/accounts")
public interface AccountClient {

	@GetExchange("/{accountId}/balance")
	Double getAccountBalance(
			@PathVariable("accountId") String accountId
	);
}
