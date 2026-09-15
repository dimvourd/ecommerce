package com.app.ecom.product.repository;

import com.app.ecom.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findAllByActiveTrue();

	Optional<Product> findByIdAndActiveTrue(Long id);

	List<Product> findDistinctByActiveTrueAndNameContainingIgnoreCaseOrActiveTrueAndDescriptionContainingIgnoreCase(String keyword, String keyword2);
}
