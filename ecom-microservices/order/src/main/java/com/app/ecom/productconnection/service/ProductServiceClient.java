package com.app.ecom.productconnection.service;

import com.app.ecom.productconnection.dtos.ProductResponse;
import com.app.ecom.productconnection.restclient.ProductHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceClient {

	private final ProductHttpClient productHttpClient;

	public ProductResponse getProductById(Long id) {
		ResponseEntity<ProductResponse> response = productHttpClient.getProductById(id);
		if (response.getStatusCode().is2xxSuccessful()) {
			return response.getBody();
		}
		throw new RuntimeException("Product not found") ;
	}
}
