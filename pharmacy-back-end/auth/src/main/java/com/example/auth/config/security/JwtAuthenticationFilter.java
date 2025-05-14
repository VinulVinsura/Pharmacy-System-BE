package com.example.auth.config.security;

import com.example.auth.service.CustomerDetailsService;
import com.example.auth.service.impl.JwtTokenService;
import jakarta.annotation.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenService jwtService;
    private CustomerDetailsService customerDetailsService;

    public JwtAuthenticationFilter(JwtTokenService jwtService, CustomerDetailsService customerDetailsService) {
        this.jwtService = jwtService;
        this.customerDetailsService = customerDetailsService;
    }


    @Override
    protected void doFilterInternal(@Nullable HttpServletRequest request,
                                    @Nullable HttpServletResponse response,
                                    @Nullable FilterChain filterChain) throws ServletException, IOException {



        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            if(request.getServletPath().equals("/auth/login"))
            {

                filterChain.doFilter(request,response);
                return;
            }
        }
        String token=authHeader.substring(7);
        String username=jwtService.extractUsername(token);
        if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null){

            if(!jwtService.isValidToken(token)){

                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Unauthorized : Invalid Token");
                response.getWriter().flush();
                return;
            }
            UserDetails userDetail = customerDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                    userDetail,null,userDetail.getAuthorities()
            );

            authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request,response);


    }
}
