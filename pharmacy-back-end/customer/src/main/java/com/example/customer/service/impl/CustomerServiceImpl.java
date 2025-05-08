package com.example.customer.service.impl;

import com.example.customer.dto.request.CustomerDto;
import com.example.customer.dto.response.ApiResponse;
import com.example.customer.entity.Customer;
import com.example.customer.enums.Role;
import com.example.customer.repository.CustomerRepo;
import com.example.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    @Override
    public ResponseEntity<ApiResponse> save(CustomerDto customerDto) {

      try {
          if(customerRepo.existsById(customerDto.getCustomerId())){
              // update customer
              Optional<Customer> byId = customerRepo.findById(customerDto.getCustomerId());
              byId.get().setFullName(customerDto.getFullName());
              byId.get().setUserName(customerDto.getUserName());
              byId.get().setEmail(customerDto.getEmail());
              byId.get().setContact(customerDto.getContact());
              byId.get().setCity(customerDto.getCity());
              byId.get().setCounty(customerDto.getCounty());
              byId.get().setAddress(customerDto.getAddress());
              byId.get().setUpdatedTime(new Date());
              Customer updated = customerRepo.save(byId.get());
              return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED)
                      .body(new ApiResponse(HttpStatus.UPGRADE_REQUIRED.value(),"Customer Updated",updated));

          }

          Customer customer= Customer.builder()
                  .fullName(customerDto.getFullName())
                  .userName(customerDto.getUserName())
                  .password(customerDto.getPassword())
                  .email(customerDto.getEmail())
                  .contact(customerDto.getContact())
                  .city(customerDto.getCity())
                  .county(customerDto.getCounty())
                  .address(customerDto.getAddress())
                  .role(Role.CUSTOMER)
                  .createdTime(new Date())
                  .updatedTime(new Date())
                  .build();
          Customer saved = customerRepo.save(customer);
          return ResponseEntity.status(HttpStatus.CREATED)
                  .body(new ApiResponse(HttpStatus.CREATED.value(), "Customer Saved",saved));

      }catch (Exception ex){
          ex.printStackTrace();
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null));
      }

    }
}
