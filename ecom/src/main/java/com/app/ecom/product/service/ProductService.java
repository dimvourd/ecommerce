package com.app.ecom.product.service;

import com.app.ecom.product.entity.Product;
import com.app.ecom.product.entity.dto.ProductRequest;
import com.app.ecom.product.entity.dto.ProductResponse;
import com.app.ecom.product.mapper.ProductMapper;
import com.app.ecom.product.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductMapper mapper;
	private final ProductRepository repository;

	@Transactional
	public ProductResponse createProduct(ProductRequest productRequest) {

		Product newProduct = mapper.toProduct(productRequest);
		return mapper.toProductResponse(repository.save(newProduct));
	}

	public List<ProductResponse> getAllProducts() {
		return repository.findAll().stream()
				.map(mapper::toProductResponse)
				.toList();
	}

	@Transactional
	public ProductResponse updateProduct(@Valid ProductRequest productRequest, Long id) {
		final Product dbProduct = this.getProductById(id);

		mapper.updateProductFromRequest(productRequest, dbProduct);

		return mapper.toProductResponse(dbProduct);
	}

	public ProductResponse getProductResponseById(Long id) {
		return mapper.toProductResponse(this.getProductById(id));
	}

	@Transactional
	public ProductResponse refillProductStock(Integer quantity, Long id) {
		Product productDB = this.getProductById(id);
		productDB.setActive(true);
		productDB.setStockQuantity(productDB.getStockQuantity() + quantity);

		return mapper.toProductResponse(productDB);
	}

	public List<ProductResponse> getAllActiveProducts() {

		return repository.findAllByActiveTrue().stream().map(mapper::toProductResponse).toList();
	}

	private Product getProductById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found: " + id));
	}

	@Transactional
	public void deleteProduct(Long id) {
		Product product = this.getProductById(id);
		repository.delete(product);
	}

	public Set<ProductResponse> searchProducts(String keyword) {

		return repository
				.findDistinctByActiveTrueAndNameContainingIgnoreCaseOrActiveTrueAndDescriptionContainingIgnoreCase(keyword, keyword)
				.stream()
				.map(mapper::toProductResponse)
				.collect(Collectors.toSet());
	}
}
