package com.app.ecom.user.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

	private String street;

	private String city;

	private String state;

	@NotNull
	private String zip;

	@NotNull
	private String country;

}
