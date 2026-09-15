package com.app.ecom.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "address_gen")
	@SequenceGenerator(name = "address_gen", sequenceName = "address_seq", allocationSize = 1)
	private Long id;

	private String street;

	private String city;

	private String state;

	@NotBlank
	private String zip;

	@NotBlank
	private String country;

}
