package com.app.ecom.productconnection.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

	private String name;
	private String description;
	private BigDecimal price;
	private Integer stockQuantity;
	private String imageUrl;
	private String category;
	private Boolean active;
}
