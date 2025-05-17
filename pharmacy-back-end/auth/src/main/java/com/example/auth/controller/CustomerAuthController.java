package com.example.auth.controller;

import com.example.auth.dto.request.LoginDto;
import com.example.auth.dto.response.ApiResponse;
import com.example.auth.service.CustomerAuthService;
import com.example.auth.service.impl.CustomerDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class CustomerAuthController {

    private CustomerAuthService customerAuthService;
    private CustomerDetailsServiceImpl detailsService;

    public CustomerAuthController(CustomerAuthService customerAuthService, CustomerDetailsServiceImpl detailsService) {
        this.customerAuthService = customerAuthService;
        this.detailsService=detailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDto loginDto){
        return customerAuthService.login(loginDto);
    }

    @GetMapping("/get-user-details/{userName}")
    public Collection<? extends GrantedAuthority> getUserDetails(@PathVariable String userName){
        UserDetails userDetails = detailsService.loadUserByUsername(userName);
        return userDetails.getAuthorities();
    }
}
