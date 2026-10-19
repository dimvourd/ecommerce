package com.app.ecom.userconnection.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String role;
	private AddressDTO address;
}
