package com.app.ecom.order.entity;


import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponse {

	private Long id;


	private OrderStatus status;

	private List<OrderItemDTO> items = new ArrayList<>();

	private BigDecimal totalPrice;
}
