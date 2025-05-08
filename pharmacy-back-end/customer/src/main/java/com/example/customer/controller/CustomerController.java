package com.example.customer.controller;

import com.example.customer.dto.request.CustomerDto;
import com.example.customer.dto.response.ApiResponse;
import com.example.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")

public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save-customer")
    public ResponseEntity<ApiResponse> saveCustomer(@RequestBody CustomerDto customerDto){
           return  customerService.save(customerDto);
    }
}
