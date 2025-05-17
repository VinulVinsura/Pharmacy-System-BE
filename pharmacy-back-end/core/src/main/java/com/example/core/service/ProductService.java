package com.example.core.service;

import com.example.core.dto.request.ProductDto;
import com.example.core.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<ApiResponse> saveProduct(ProductDto productDto);
}
