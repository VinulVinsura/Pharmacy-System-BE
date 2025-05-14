package com.example.auth.service;

import com.example.auth.dto.request.LoginDto;
import com.example.auth.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface CustomerAuthService {
    ResponseEntity<ApiResponse> login(LoginDto loginDto);
}
