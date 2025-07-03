package com.taliibHub.backend.service.implementation;

import com.taliibHub.backend.dto.AuthRequest;
import com.taliibHub.backend.dto.AuthResponse;
import com.taliibHub.backend.dto.RegisterRequest;
import com.taliibHub.backend.model.Utilisateur;
import com.taliibHub.backend.model.Etudiant;
import com.taliibHub.backend.model.Enseignant;
import com.taliibHub.backend.repository.UtilisateurRepository;
import com.taliibHub.backend.repository.EtudiantRepository;
import com.taliibHub.backend.security.JwtUtil;
import com.taliibHub.backend.enums.RoleUtilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Transactional
    public AuthResponse register(RegisterRequest req) {
        if (utilisateurRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Cet email est déjà utilisé.");
        }
        String userId = UUID.randomUUID().toString();
        Utilisateur user;
        if (req.getRole() == RoleUtilisateur.ENSEIGNANT) {
            // Création d'un enseignant
            Enseignant enseignant = new Enseignant();
            enseignant.setId(userId);
            enseignant.setNom(req.getNom());
            enseignant.setPrenom(req.getPrenom());
            enseignant.setEmail(req.getEmail());
            enseignant.setMotDePass(passwordEncoder.encode(req.getPassword()));
            enseignant.setRole(RoleUtilisateur.ENSEIGNANT);
            if (req.getSpecialite() != null) {
                enseignant.setSpecialite(req.getSpecialite());
            }
            utilisateurRepository.save(enseignant);
            user = enseignant;
        } else {
            // Par défaut ou si ETUDIANT
            Etudiant etudiant = new Etudiant();
            etudiant.setId(userId);
            etudiant.setNom(req.getNom());
            etudiant.setPrenom(req.getPrenom());
            etudiant.setEmail(req.getEmail());
            etudiant.setMotDePass(passwordEncoder.encode(req.getPassword()));
            etudiant.setRole(RoleUtilisateur.ETUDIANT);
            etudiant.setDateInscription(new Date());
            etudiantRepository.save(etudiant);
            user = etudiant;
        }
        String token = jwtUtil.generateToken(user);
        AuthResponse res = new AuthResponse();
        res.setToken(token);
        res.setUser(user);
        return res;
    }

    public AuthResponse login(AuthRequest req) {
        Etudiant etudiant = etudiantRepository.findByEmail(req.getEmail());
        logger.info("Tentative de connexion pour l'email : {}", req.getEmail());
        if (etudiant == null) {
            logger.warn("Aucun utilisateur trouvé pour l'email : {}", req.getEmail());
        } else {
            logger.info("Mot de passe reçu : {}", req.getPassword());
            logger.info("Hash stocké : {}", etudiant.getMotDePass());
            boolean match = passwordEncoder.matches(req.getPassword(), etudiant.getMotDePass());
            logger.info("Résultat du passwordEncoder.matches : {}", match);
        }
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
