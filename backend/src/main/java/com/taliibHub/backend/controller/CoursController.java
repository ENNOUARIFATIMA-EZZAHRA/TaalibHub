package com.taliibHub.backend.controller;

import com.taliibHub.backend.model.Cours;
import com.taliibHub.backend.model.Utilisateur;
import com.taliibHub.backend.repository.CoursRepository;
import com.taliibHub.backend.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiant")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class CoursController {
    
    @Autowired
    private CoursRepository coursRepository;
    
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    @GetMapping("/cours")
    public ResponseEntity<List<Cours>> getMesCours(Authentication authentication) {
        System.out.println("📥 Requête reçue pour les cours de l'étudiant");
        System.out.println("🔍 CoursController - getMesCours called");
        
        if (authentication == null || authentication.getPrincipal() == null) {
            System.out.println("❌ CoursController - No authentication found");
            return ResponseEntity.status(401).build();
        }
        
        Utilisateur user = (Utilisateur) authentication.getPrincipal();
        System.out.println("🔍 CoursController - User authenticated: " + user.getEmail());
        
        try {


            List<Cours> cours = coursRepository.findAll();
            System.out.println("CoursController - Found " + cours.size() + " courses");
            return ResponseEntity.ok(cours);
        } catch (Exception e) {
            System.out.println("CoursController - Error fetching courses: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}
