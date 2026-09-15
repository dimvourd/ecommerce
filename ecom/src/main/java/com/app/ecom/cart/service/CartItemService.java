package com.app.ecom.cart.service;

import com.app.ecom.cart.entity.CartItem;
import com.app.ecom.cart.entity.dto.CartItemResponse;
import com.app.ecom.cart.mapper.CartItemMapper;
import com.app.ecom.cart.repository.CartItemRepository;
import com.app.ecom.product.entity.Product;
import com.app.ecom.product.repository.ProductRepository;
import com.app.ecom.user.entity.User;
import com.app.ecom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemService {

	private final CartItemRepository repository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final CartItemMapper mapper;

	@Transactional
	public void addToCart(Long userId, Long productId, Integer quantity) {

		Product product = productRepository.findByIdAndActiveTrue(productId)
				.orElseThrow(() -> new RuntimeException("Not an active Product found: " + productId));


		Optional<CartItem> cartItemOpt = repository.findByUserIdAndProductId(userId, productId);
		CartItem cartItem;
		if (cartItemOpt.isEmpty()) {

			this.validateProductAndCartItemFinalQuantity(quantity, product);

			cartItem = new CartItem();

			User user = userRepository.findById(userId)
					.orElseThrow(() -> new RuntimeException("User not found: " + userId));

			cartItem.setProduct(product);
			cartItem.setUser(user);
			cartItem.setQuantity(quantity);
		} else {
			cartItem = cartItemOpt.get();
			final Integer cartFinalQuantity = cartItem.getQuantity() + quantity;
			this.validateProductAndCartItemFinalQuantity(cartFinalQuantity, product);

			cartItem.setQuantity(cartFinalQuantity);
		}

		BigDecimal totalCartItemPrice = product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
		cartItem.setPrice(totalCartItemPrice);

		repository.save(cartItem);
	}

	@Transactional
	public void removeCartItem(Long userId, Long productId, Integer removingQuantity) {

		CartItem cartItem = repository.findByUserIdAndProductId(userId, productId)
				.orElseThrow(() -> new RuntimeException("Cart item not found By user: " + userId +
						" and product: " + productId));
		try {

			Product prod = productRepository.findByIdAndActiveTrue(productId)
					.orElseThrow(() -> new RuntimeException("Not an active Product found: " + productId));

			if(null == removingQuantity || removingQuantity >= cartItem.getQuantity()) {

				repository.delete(cartItem);
				return;
			}
			if(removingQuantity == 0) return;

			Integer finalQuantity = cartItem.getQuantity() - removingQuantity;
			this.validateProductAndCartItemFinalQuantity(finalQuantity, prod);
			cartItem.setQuantity(finalQuantity);

			BigDecimal totalCartItemPrice = prod.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
			cartItem.setPrice(totalCartItemPrice);
		} catch (RuntimeException e) {
			repository.delete(cartItem);
			throw e;
		}
	}

	public List<CartItem> findByUserId(Long userId) {
		return repository.findByUserId(userId);
	}

	public List<CartItemResponse> getCartItemsByUser(Long userId) {
		return repository.findByUserId(userId).stream()
				.map(mapper::toCartItemResponse)
				.toList();
	}

	@Transactional
	public void clearCartAfterOrder(Long userId) {
		List<CartItem> cartItems = repository.findByUserId(userId);
		for(CartItem cartItem : cartItems) {
			Product product = cartItem.getProduct();
			this.validateProductAndCartItemFinalQuantity(cartItem.getQuantity(), product);
			// Removing the ordered products from the stock
			product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
		}


		repository.deleteByUserId(userId);
	}

	private void validateProductAndCartItemFinalQuantity(Integer cartFinalQuantity, Product product) {
		if(cartFinalQuantity > product.getStockQuantity())
			throw new RuntimeException("Insufficient stock for product: " + product.getId());
	}

}
