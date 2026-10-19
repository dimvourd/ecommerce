package com.app.ecom.userconnection.restclient;

import com.app.ecom.userconnection.dtos.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/api/v1/users")
public interface UserHttpClient {

	@GetExchange("/{userId}")
	ResponseEntity<UserResponse> getUserById(@PathVariable(name = "userId") String userId);
}
