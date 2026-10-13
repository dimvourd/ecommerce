package com.app.ecom.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

	private Long id;
	private Integer quantity;
	private BigDecimal price;
	private Long productId;
}
