package com.example.core.service;

import com.example.core.dto.request.CategoryDto;
import com.example.core.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<ApiResponse> saveCategory(CategoryDto categoryDto);

    ResponseEntity<ApiResponse> getAllCategory();

    ResponseEntity<ApiResponse> getCategoryById(Integer catId);

    ResponseEntity<ApiResponse> deleteCategory(Integer catId);
}
