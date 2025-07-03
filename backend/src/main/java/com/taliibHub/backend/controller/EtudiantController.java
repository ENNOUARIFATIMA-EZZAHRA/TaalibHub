package com.taliibHub.backend.controller;

import com.taliibHub.backend.dto.EtudiantDashboardDTO;
import com.taliibHub.backend.dto.EtudiantRequest;
import com.taliibHub.backend.model.Cours;
import com.taliibHub.backend.model.Etudiant;
import com.taliibHub.backend.repository.CoursRepository;
import com.taliibHub.backend.service.implementation.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/etudiant")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class EtudiantController {
    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private EtudiantService etudiantService;

    @GetMapping("/dashboard/{num}")
    public EtudiantDashboardDTO getDashboard(@PathVariable int num) {
        Etudiant etudiant = etudiantService.getEtudiantByNum(num);
        EtudiantDashboardDTO dto = new EtudiantDashboardDTO();
        dto.etudiant = etudiant;
        dto.cours = coursRepository.findAll();
        return dto;
    }

    @GetMapping("/all")
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable String id) {
        Optional<Etudiant> etudiant = etudiantService.getEtudiantById(id);
        return etudiant.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Etudiant> getEtudiantByEmail(@PathVariable String email) {
        Etudiant etudiant = etudiantService.getEtudiantByEmail(email);
        if (etudiant != null) {
            return ResponseEntity.ok(etudiant);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEtudiant(@RequestBody EtudiantRequest request) {
        try {
            Etudiant savedEtudiant = etudiantService.createEtudiant(request);
            return ResponseEntity.ok(savedEtudiant);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating etudiant: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEtudiant(@PathVariable String id, @RequestBody EtudiantRequest request) {
        try {
            Etudiant updatedEtudiant = etudiantService.updateEtudiant(id, request);
            if (updatedEtudiant != null) {
                return ResponseEntity.ok(updatedEtudiant);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating etudiant: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEtudiant(@PathVariable String id) {
        boolean deleted = etudiantService.deleteEtudiant(id);
        if (deleted) {
            return ResponseEntity.ok().body("Etudiant deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
