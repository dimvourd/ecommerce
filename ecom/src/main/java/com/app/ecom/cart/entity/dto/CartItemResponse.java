package com.app.ecom.cart.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {

	private String productName;

	private BigDecimal productPrice;

	private Integer totalProductQuantity;

	private BigDecimal totalProductPrice;
}
