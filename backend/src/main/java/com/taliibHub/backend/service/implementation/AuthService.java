package com.taliibHub.backend.service.implementation;

import com.taliibHub.backend.dto.AuthRequest;
import com.taliibHub.backend.dto.AuthResponse;
import com.taliibHub.backend.dto.RegisterRequest;
import com.taliibHub.backend.model.Utilisateur;
import com.taliibHub.backend.repository.UtilisateurRepository;
import com.taliibHub.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest req) {
        if (utilisateurRepository.findByEmail(req.getEmail()) != null) {
            throw new RuntimeException("Cet email est déjà utilisé.");
        }
        Utilisateur user = new Utilisateur();
        user.setId(UUID.randomUUID().toString());
        user.setNom(req.getNom());
        user.setPrenom(req.getPrenom());
        user.setEmail(req.getEmail());
        user.setMotDePass(passwordEncoder.encode(req.getMotDePass()));
        user.setRole(req.getRole());
        utilisateurRepository.save(user);
        String token = jwtUtil.generateToken(user);
        AuthResponse res = new AuthResponse();
        res.setToken(token);
        return res;
    }

    public AuthResponse login(AuthRequest req) {
        Utilisateur user = utilisateurRepository.findByEmail(req.getEmail());
        if (user == null || !passwordEncoder.matches(req.getMotDePass(), user.getMotDePass())) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }
        String token = jwtUtil.generateToken(user);
        AuthResponse res = new AuthResponse();
        res.setToken(token);
        return res;
    }

    public Utilisateur getCurrentUser(String email) {
        return utilisateurRepository.findByEmail(email);
    }
}
