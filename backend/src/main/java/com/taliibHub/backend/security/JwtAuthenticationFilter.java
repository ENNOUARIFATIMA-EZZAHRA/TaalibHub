package com.taliibHub.backend.security;


import com.taliibHub.backend.model.Utilisateur;
import com.taliibHub.backend.repository.UtilisateurRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        String email = null;
        String jwt = null;

        System.out.println("JwtAuthenticationFilter - Processing request: " + request.getServletPath());
        System.out.println("JwtAuthenticationFilter - Authorization header: " + (authHeader != null ? "Present" : "Missing"));

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            System.out.println("JWT reçu dans la requête: " + jwt);
            System.out.println("JwtAuthenticationFilter - JWT token extracted: " + (jwt != null ? "Yes" : "No"));
            
            try {
                email = jwtUtil.extractEmail(jwt);
                System.out.println("Email extrait du JWT: " + email);
            } catch (ExpiredJwtException e) {
                System.out.println("JWT expiré");
                System.out.println("Détail: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("JWT invalide");
                System.out.println("JWT invalide: " + e.getMessage());
            }
        } else {
            System.out.println("JwtAuthenticationFilter - No Authorization header or invalid format");
        }

        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByEmail(email);
        if (utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();
            System.out.println("JwtAuthenticationFilter - User found: " + utilisateur.getEmail());
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    utilisateur, null, 
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().name()))
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
            System.out.println("JwtAuthenticationFilter - Authentication set successfully");
        } else if (email == null) {
            System.out.println("JwtAuthenticationFilter - No email extracted from token");
        } else {
            System.out.println("JwtAuthenticationFilter - Authentication already exists");
        }
        
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(jakarta.servlet.http.HttpServletRequest request) throws jakarta.servlet.ServletException {
        String path = request.getServletPath();
        boolean shouldNotFilter = path.startsWith("/api/auth/");
        System.out.println(" JwtAuthenticationFilter - Should not filter path '" + path + "': " + shouldNotFilter);
        return shouldNotFilter;
    }
} 