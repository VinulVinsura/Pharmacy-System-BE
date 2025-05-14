package com.example.auth.service.impl;

import com.example.auth.repository.CustomerRepo;
import com.example.auth.service.CustomerDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService, UserDetailsService {

    private CustomerRepo customerRepo;

    public CustomerDetailsServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return customerRepo.findByUserName(username).orElseThrow(
               ()->new UsernameNotFoundException("Customer is not found..")
       );
    }
}
