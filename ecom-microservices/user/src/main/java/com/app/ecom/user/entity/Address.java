package com.app.ecom.user.entity;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "addresses")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	@Id
	private String id;

	private String street;

	private String city;

	private String state;

	@NotBlank
	private String zip;

	@NotBlank
	private String country;

}
