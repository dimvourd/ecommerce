package com.app.ecom.order.controller;


import com.app.ecom.order.entity.OrderResponse;
import com.app.ecom.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(@RequestHeader("X-User-ID") Long userId) {

		return ResponseEntity.created(URI.create("/api/v1/orders"))
				.body(orderService.createOrder(userId));
	}

	@GetMapping
	public ResponseEntity<List<OrderResponse>> getOrdersByUser(@RequestHeader("X-User-ID") Long userId) {

		return ResponseEntity.ok(orderService.getOrdersByUser(userId));
	}
}
