package com.taliibHub.backend.service.implementation;

import com.taliibHub.backend.dto.AuthRequest;
import com.taliibHub.backend.dto.AuthResponse;
import com.taliibHub.backend.dto.RegisterRequest;
import com.taliibHub.backend.model.Utilisateur;
import com.taliibHub.backend.model.Etudiant;
import com.taliibHub.backend.repository.UtilisateurRepository;
import com.taliibHub.backend.repository.EtudiantRepository;
import com.taliibHub.backend.security.JwtUtil;
import com.taliibHub.backend.enums.RoleUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.util.Date;

@Service
public class AuthService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public AuthResponse register(RegisterRequest req) {
        if (etudiantRepository.findByEmail(req.getEmail()) != null) {
            throw new RuntimeException("Cet email est déjà utilisé.");
        }
        
        String userId = UUID.randomUUID().toString();
        
        // Create and save Etudiant (which extends Utilisateur)
        Etudiant etudiant = new Etudiant();
        etudiant.setId(userId);
        etudiant.setNom(req.getNom());
        etudiant.setEmail(req.getEmail());
        etudiant.setMotDePass(passwordEncoder.encode(req.getPassword()));
        etudiant.setRole(RoleUtilisateur.ETUDIANT);
        etudiant.setDateInscription(new Date());
        etudiantRepository.save(etudiant);
        
        String token = jwtUtil.generateToken(etudiant);
        AuthResponse res = new AuthResponse();
        res.setToken(token);
        res.setUser(etudiant);
        return res;
    }

    public AuthResponse login(AuthRequest req) {
        Etudiant etudiant = etudiantRepository.findByEmail(req.getEmail());
        if (etudiant == null || !passwordEncoder.matches(req.getPassword(), etudiant.getMotDePass())) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }
        String token = jwtUtil.generateToken(etudiant);
        AuthResponse res = new AuthResponse();
        res.setToken(token);
        res.setUser(etudiant);
        return res;
    }

    public Utilisateur getCurrentUser(String email) {
        return etudiantRepository.findByEmail(email);
    }
}
