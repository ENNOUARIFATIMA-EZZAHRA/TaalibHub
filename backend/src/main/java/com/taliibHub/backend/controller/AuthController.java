package com.taliibHub.backend.controller;

import com.taliibHub.backend.dto.AuthRequest;
import com.taliibHub.backend.dto.RegisterRequest;
import com.taliibHub.backend.model.Utilisateur;
import com.taliibHub.backend.service.implementation.AuthService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.taliibHub.backend.dto.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class AuthController {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        logger.info("Received registration request for email: {}", registerRequest.getEmail());
        logger.info("Registration data: nom={}, email={}, password length={}", 
                   registerRequest.getNom(), registerRequest.getEmail(), 
                   registerRequest.getPassword() != null ? registerRequest.getPassword().length() : 0);
        
        try {
            if (registerRequest.getNom() == null || registerRequest.getNom().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Name is required");
                return ResponseEntity.badRequest().body(error);
            }
            
            if (registerRequest.getEmail() == null || registerRequest.getEmail().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Email is required");
                return ResponseEntity.badRequest().body(error);
            }
            
            if (registerRequest.getPassword() == null || registerRequest.getPassword().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Password is required");
                return ResponseEntity.badRequest().body(error);
            }
            
            return ResponseEntity.ok(authService.register(registerRequest));
        } catch (RuntimeException e) {
            logger.error("Registration error: {}", e.getMessage(), e);
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        logger.info("Received login request for email: {}", authRequest.getEmail());
        
        try {
            return ResponseEntity.ok(authService.login(authRequest));
        } catch (RuntimeException e) {
            logger.error("Login error: {}", e.getMessage(), e);
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/test")
    public ResponseEntity<?> testAuth() {
        logger.info("Testing authentication endpoint");
        Map<String, String> response = new HashMap<>();
        response.put("message", "Authentication working correctly");
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public ResponseEntity<?> testAuthGet() {
        logger.info("Testing GET authentication endpoint");
        Map<String, String> response = new HashMap<>();
        response.put("message", "GET Authentication working correctly");
        response.put("status", "success");
        response.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test-auth")
    public ResponseEntity<?> testAuth(Authentication authentication) {
        System.out.println("AuthController - testAuth called");
        
        if (authentication == null || authentication.getPrincipal() == null) {
            System.out.println("AuthController - No authentication found");
            return ResponseEntity.status(401).body("No authentication");
        }
        
        Utilisateur user = (Utilisateur) authentication.getPrincipal();
        System.out.println("AuthController - User authenticated: " + user.getEmail());
        
        return ResponseEntity.ok(Map.of(
            "message", "Authentication successful",
            "user", user.getEmail(),
            "role", user.getRole().name()
        ));
    }
}
