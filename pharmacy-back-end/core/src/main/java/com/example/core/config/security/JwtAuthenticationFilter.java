package com.example.core.config.security;

import com.example.core.feign.AuthControllerClient;
import com.example.core.service.impl.JwtTokenService;
import jakarta.annotation.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenService jwtService;
    private AuthControllerClient authControllerClient;
    private static final Logger log = LoggerFactory.getLogger(JwtTokenService.class);

    public JwtAuthenticationFilter(JwtTokenService jwtService ,AuthControllerClient client) {
        this.jwtService = jwtService;
        this.authControllerClient=client;
    }


    @Override
    protected void doFilterInternal(@Nullable HttpServletRequest request,
                                    @Nullable HttpServletResponse response,
                                    @Nullable FilterChain filterChain) throws ServletException, IOException {


        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request,response);
                return;
        }

        String token=authHeader.substring(7);
        String username=jwtService.extractUserName(token);

        if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null){

            if(!jwtService.isValid(token)){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized : Invalid Token");
                response.getWriter().flush();
                return;
            }
            Collection<? extends GrantedAuthority> grantedAuthorities = authControllerClient.getUserDetails(username);
            log.error("==============================================");
//            log.info(grantedAuthorities.toString());
//            UserDetails userDetail = customerDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                    username,null,grantedAuthorities
            );

            log.error("+++++++++++++++++++++++++++++++++++++++++++++++++");

            authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request,response);


    }
}
