package com.app.ecom.userconnection.service;


import com.app.ecom.userconnection.dtos.UserResponse;
import com.app.ecom.userconnection.restclient.UserHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceClient {

	private final UserHttpClient userHttpClient;

	public UserResponse getUserById(String userId){
		ResponseEntity<UserResponse>  response = userHttpClient.getUserById(userId);

		if(response.getStatusCode().is2xxSuccessful()){
			return response.getBody();
		}
		throw new RuntimeException("User Not Found");
	}
}
