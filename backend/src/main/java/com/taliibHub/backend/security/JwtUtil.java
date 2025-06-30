package com.taliibHub.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.taliibHub.backend.model.Utilisateur;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;
    
    @Value("${jwt.expiration:86400000}") // 24 hours default
    private long expirationTime;

    public String generateToken(Utilisateur user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);
        
        System.out.println("JwtUtil - Generating token for user: " + user.getEmail());
        System.out.println("JwtUtil - Token expiration: " + expiryDate);
        
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

    public String extractEmail(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
        
        System.out.println("JwtUtil - Extracted email: " + claims.getSubject());
        System.out.println("JwtUtil - Token expiration: " + claims.getExpiration());
        System.out.println("JwtUtil - Token issued at: " + claims.getIssuedAt());
        
        return claims.getSubject();
    }
    
    public boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            System.out.println("JwtUtil - Error checking token expiration: " + e.getMessage());
            return true;
        }
    }
}
