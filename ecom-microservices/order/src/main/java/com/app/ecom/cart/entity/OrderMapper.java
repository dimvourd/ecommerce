package com.app.ecom.cart.entity;

import com.app.ecom.order.entity.Order;
import com.app.ecom.order.entity.OrderItem;
import com.app.ecom.order.entity.OrderItemDTO;
import com.app.ecom.order.entity.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

	@Mapping(target = "order", source = "order")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	OrderItem toOrderItem(CartItem cartItem, Order order);


	@Mapping(target = "items", source = "order.items")
	OrderResponse toOrderResponse(Order order);

	OrderItemDTO toOrderItemDTO(OrderItem orderItem);
}
