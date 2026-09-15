package com.app.ecom.order.entity;


import com.app.ecom.user.entity.dto.UserResponse;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponse {

	private Long id;

	private UserResponse user;

	private OrderStatus status;

	private List<OrderItemDTO> items = new ArrayList<>();

	private BigDecimal totalPrice;
}
