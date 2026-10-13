package com.app.ecom.cart.controller;


import com.app.ecom.cart.entity.dto.CartItemResponse;
import com.app.ecom.cart.service.CartItemService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/cart-items")
@RequiredArgsConstructor
public class CartItemController {

	private final CartItemService service;

	@GetMapping("/user")
	public ResponseEntity<List<CartItemResponse>> getCart(@RequestHeader("X-User-ID") Long userId) {
		List<CartItemResponse> cartItemsByUser = service.getCartItemsByUser(userId);
		return ResponseEntity.ok().body(cartItemsByUser);
	}

	@PostMapping("/add")
	public ResponseEntity<Void> addToCart(@RequestHeader("X-User-ID") @NotNull Long userId,
	                                      @NotNull Long productId, @NotNull @Positive Integer quantity) {
		service.addToCart(userId, productId, quantity);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/remove-products")
	public ResponseEntity<Void> removeCartItem(@RequestHeader("X-User-ID") Long userId,
	                                           @RequestParam Long productId,
	                                           @RequestParam(required = false) Integer quantity) {

		service.removeCartItem(userId, productId, quantity);
		return ResponseEntity.ok().build();
	}
}
