package com.example.customer.service;

import com.example.customer.dto.request.CustomerDto;
import com.example.customer.dto.request.LoginDto;
import com.example.customer.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<ApiResponse> save(CustomerDto customerDto);

    ResponseEntity<ApiResponse> login(LoginDto loginDto);
}
