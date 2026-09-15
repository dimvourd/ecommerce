package com.app.ecom.order.service;


import com.app.ecom.cart.entity.CartItem;
import com.app.ecom.cart.entity.OrderMapper;
import com.app.ecom.cart.repository.CartItemRepository;
import com.app.ecom.cart.service.CartItemService;
import com.app.ecom.order.entity.Order;
import com.app.ecom.order.entity.OrderItem;
import com.app.ecom.order.entity.OrderResponse;
import com.app.ecom.order.entity.OrderStatus;
import com.app.ecom.order.repository.OrderRepository;
import com.app.ecom.user.entity.User;
import com.app.ecom.user.repository.UserRepository;
import com.app.ecom.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository repository;
	private final UserService userService;
	private final CartItemService cartItemService;
	private final OrderMapper orderMapper;

	@Transactional
	public OrderResponse createOrder(Long userId) {

		// Validate the user
		User user = userService.fetchUser(userId);

		// Validate the cart items
		List<CartItem> cartItems = cartItemService.findByUserId(userId);
		if(cartItems.isEmpty()) {
			throw new RuntimeException("Cart is empty. Please add items to the cart before placing an order.");
		}

		// Calculate the total price
		BigDecimal totalPrice = cartItems.stream()
				.map(CartItem::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);


		// Create the order
		Order order = new Order();
		order.setUser(user);
		order.setStatus(OrderStatus.CONFIRMED);
		order.setTotalPrice(totalPrice);
		List<OrderItem> orderItems = cartItems.stream()
				.map(cartItem -> orderMapper.toOrderItem(cartItem, order))
				.toList();
		order.setItems(orderItems);

		// Clear the cart
		cartItemService.clearCartAfterOrder(userId);

		return orderMapper.toOrderResponse(
				repository.save(order)
		);
	}

	public List<OrderResponse> getOrdersByUser(Long userId) {

		return repository.findByUserId(userId).stream()
				.map(orderMapper::toOrderResponse)
				.toList();
	}
}
