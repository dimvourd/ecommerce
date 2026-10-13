package com.app.ecom.product.controller;

import com.app.ecom.product.entity.dto.ProductRequest;
import com.app.ecom.product.entity.dto.ProductResponse;
import com.app.ecom.product.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {

		return new ResponseEntity<>(productService.getProductResponseById(id), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {

		return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable Long id) {

		return new ResponseEntity<>(productService.updateProduct(productRequest, id), HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ProductResponse> refillProductStock(@RequestParam Integer quantity, @PathVariable Long id) {

		return ResponseEntity.ok(productService.refillProductStock(quantity, id));
	}

	@GetMapping("/active")
	public ResponseEntity<List<ProductResponse>> getAllActiveProducts() {
		return new ResponseEntity<>(productService.getAllActiveProducts(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public ResponseEntity<Set<ProductResponse>> searchProducts(@RequestParam @NotBlank String keyword) {

		return ResponseEntity.ok(productService.searchProducts(keyword));
	}
}
