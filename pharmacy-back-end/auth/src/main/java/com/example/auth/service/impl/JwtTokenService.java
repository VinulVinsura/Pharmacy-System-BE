package com.example.auth.service.impl;

import com.example.auth.entity.Customer;
import com.example.auth.repository.CustomerRepo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.lang.ref.SoftReference;
import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenService {

    @Value("${app.auth.tokenSecret}")
    private String secretKey;

    private static final Logger log = LoggerFactory.getLogger(JwtTokenService.class);

    private CustomerRepo customerRepo;

    public JwtTokenService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public String createToken(String username){
        try {
            Optional<Customer> byUserName = customerRepo.findByUserName(username);
            log.error("=================================================");
            log.info(byUserName.get().getFullName());
            String token= Jwts.
                    builder()
                    .setSubject(byUserName.get().getUsername())
                    .claim("id",byUserName.get().getCustomerId())
                    .claim("FullName",byUserName.get().getFullName())
                    .claim("Role",byUserName.get().getRole())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 60*60*24*1000))
                    .signWith(getSignKey())
                    .compact();
            return token;

        }catch (Exception ex){
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    private SecretKey getSignKey(){
        byte[] decode = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(decode);
    }



    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.get("username", String.class);
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            log.error("Invalid JWT signature");
        }catch (MalformedJwtException ex){
            log.error("Invalid JWT token");
        }catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
