//package com.ecommerce.product.controller;
//
//import com.ecommerce.product.dto.ProductRequest;
//import com.ecommerce.product.dto.ProductResponse;
//import com.ecommerce.product.exception.ResourceNotFoundException;
//import com.ecommerce.product.service.ProductService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.mockito.Mockito;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import static org.hamcrest.Matchers.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.doThrow;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(ProductController.class)
//class ProductControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private ProductService productService;
//
//    @TestConfiguration
//    static class MockConfig {
//        @Bean
//        ProductService productService() {
//            return Mockito.mock(ProductService.class);
//        }
//    }
//
//    private ProductResponse sampleResponse(long id) {
//        ProductResponse r = new ProductResponse();
//        r.setId(id);
//        r.setName("Phone");
//        r.setDescription("A smartphone");
//        r.setPrice(new BigDecimal("599.99"));
//        r.setSku("SKU-" + id);
//        r.setStockQuantity(10);
//        r.setActive(true);
//        r.setCategory("Electronics");
//        return r;
//    }
//
//    private ProductRequest sampleRequest() {
//        ProductRequest req = new ProductRequest();
//        req.setName("Phone");
//        req.setDescription("A smartphone");
//        req.setPrice(new BigDecimal("599.99"));
//        req.setSku("SKU-1");
//        req.setStockQuantity(10);
//        req.setActive(true);
//        req.setCategory("Electronics");
//        return req;
//    }
//
//    @Test
//    @DisplayName("POST /api/products should create and return 201 with Location header")
//    void createProduct() throws Exception {
//        ProductResponse created = sampleResponse(1L);
//        given(productService.create(any(ProductRequest.class))).willReturn(created);
//
//        mockMvc.perform(post("/api/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(sampleRequest())))
//                .andExpect(status().isCreated())
//                .andExpect(header().string("Location", "/api/products/1"))
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("Phone")))
//                .andExpect(jsonPath("$.sku", is("SKU-1")))
//                .andExpect(jsonPath("$.stockQuantity", is(10)))
//                .andExpect(jsonPath("$.active", is(true)))
//                .andExpect(jsonPath("$.category", is("Electronics")));
//    }
//
//    @Test
//    @DisplayName("GET /api/products/{id} should return 200 with product")
//    void getById() throws Exception {
//        given(productService.getById(1L)).willReturn(sampleResponse(1L));
//
//        mockMvc.perform(get("/api/products/{id}", 1L))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("Phone")))
//                .andExpect(jsonPath("$.stockQuantity", is(10)))
//                .andExpect(jsonPath("$.active", is(true)))
//                .andExpect(jsonPath("$.category", is("Electronics")));
//    }
//
//    @Test
//    @DisplayName("GET /api/products should return list")
//    void getAll() throws Exception {
//        given(productService.getAll()).willReturn(List.of(sampleResponse(1L), sampleResponse(2L)));
//
//        mockMvc.perform(get("/api/products"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[1].id", is(2)));
//    }
//
//    @Test
//    @DisplayName("PUT /api/products/{id} should update and return 200")
//    void updateProduct() throws Exception {
//        ProductRequest updateReq = new ProductRequest();
//        updateReq.setName("Phone X");
//        updateReq.setDescription("Updated");
//        updateReq.setPrice(new BigDecimal("699.99"));
//        updateReq.setSku("SKU-1");
//        updateReq.setStockQuantity(5);
//        updateReq.setActive(false);
//        updateReq.setCategory("Mobiles");
//
//        ProductResponse updated = sampleResponse(1L);
//        updated.setName("Phone X");
//        updated.setDescription("Updated");
//        updated.setPrice(new BigDecimal("699.99"));
//        updated.setStockQuantity(5);
//        updated.setActive(false);
//        updated.setCategory("Mobiles");
//
//        given(productService.update(eq(1L), any(ProductRequest.class))).willReturn(updated);
//
//        mockMvc.perform(put("/api/products/{id}", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updateReq)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("Phone X")))
//                .andExpect(jsonPath("$.price", is(699.99)))
//                .andExpect(jsonPath("$.stockQuantity", is(5)))
//                .andExpect(jsonPath("$.active", is(false)))
//                .andExpect(jsonPath("$.category", is("Mobiles")));
//    }
//
//    @Test
//    @DisplayName("DELETE /api/products/{id} should return 204")
//    void deleteProduct() throws Exception {
//        doNothing().when(productService).delete(1L);
//
//        mockMvc.perform(delete("/api/products/{id}", 1L))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    @DisplayName("GET /api/products/{id} should return 404 when not found")
//    void getByIdNotFound() throws Exception {
//        given(productService.getById(99L)).willThrow(new ResourceNotFoundException("not found"));
//
//        mockMvc.perform(get("/api/products/{id}", 99L))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @DisplayName("DELETE /api/products/{id} should return 404 when not found")
//    void deleteNotFound() throws Exception {
//        doThrow(new ResourceNotFoundException("not found")).when(productService).delete(99L);
//
//        mockMvc.perform(delete("/api/products/{id}", 99L))
//                .andExpect(status().isNotFound());
//    }
//}
