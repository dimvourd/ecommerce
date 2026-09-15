package com.app.ecom.product.entity.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
	private String name;
	private String description;

	@DecimalMin(value = "0.01", message = "Price must be greater than 0.01")
	private BigDecimal price;

	@PositiveOrZero(message = "Stock quantity must be greater than or equal to 0")
	private Integer stockQuantity;
	private String imageUrl;
	private String category;

}
