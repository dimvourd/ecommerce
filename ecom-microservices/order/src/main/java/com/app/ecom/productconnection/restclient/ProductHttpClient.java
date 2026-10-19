package com.app.ecom.productconnection.restclient;

import com.app.ecom.productconnection.dtos.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/api/v1/products")
public interface ProductHttpClient {

	@GetExchange("/{id}")
	ResponseEntity<ProductResponse> getProductById(@PathVariable Long id);
}
