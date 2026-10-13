package com.app.ecom.cart.repository;

import com.app.ecom.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);

	List<CartItem> findByUserId(Long userId);

	void deleteByUserId(Long userId);
}
