package com.example.core.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@FeignClient(name = "auth")
public interface AuthControllerClient {

    @GetMapping("/auth-service/auth/get-user-details/{userName}")
    Collection<? extends GrantedAuthority> getUserDetails(@PathVariable String userName);
}
