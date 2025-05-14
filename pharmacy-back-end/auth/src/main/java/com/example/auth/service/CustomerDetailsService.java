package com.example.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomerDetailsService {

    UserDetails loadUserByUsername(String username);
}
