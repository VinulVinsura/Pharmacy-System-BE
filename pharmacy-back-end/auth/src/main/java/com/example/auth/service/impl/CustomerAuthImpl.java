package com.example.auth.service.impl;

import com.example.auth.dto.request.LoginDto;
import com.example.auth.dto.response.ApiResponse;
import com.example.auth.entity.Customer;
import com.example.auth.repository.CustomerRepo;
import com.example.auth.service.CustomerAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerAuthImpl implements CustomerAuthService {

    private CustomerRepo customerRepo;
    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;

    public CustomerAuthImpl(CustomerRepo customerRepo, AuthenticationManager authenticationManager, JwtTokenService jwtService) {
        this.customerRepo = customerRepo;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService=jwtService;
    }


    @Override
    public ResponseEntity<ApiResponse> login(LoginDto loginDto) {

        try {
            if(loginDto.getUsername().isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).
                        body(new ApiResponse(HttpStatus.NO_CONTENT.value(), "Empty login dto",null));
            }

            Optional<Customer> byUserName = customerRepo.findByUserName(loginDto.getUsername());
            if(!byUserName.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).
                        body(new ApiResponse(HttpStatus.NOT_FOUND.value(), "Customer not found",null));
            }

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );

            String token = jwtTokenService.createToken(byUserName.get().getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(HttpStatus.OK.value(), "token created",token));


        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null));
        }
    }
}
