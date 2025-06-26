package com.taliibHub.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping("/stats")
    public ResponseEntity<String> getGlobalStats() {
        return ResponseEntity.ok("Statistiques globales");
    }

    @GetMapping("/etudiants-difficulte")
    public ResponseEntity<String> detectStrugglingStudents() {
        return ResponseEntity.ok("Étudiants en difficulté");
    }

    @GetMapping("/export-performance")
    public ResponseEntity<String> exportPerformanceData() {
        return ResponseEntity.ok("Export des performances");
    }

    @PostMapping("/maj-systeme")
    public ResponseEntity<String> manageUsersAndModules() {
        return ResponseEntity.ok("Gestion des utilisateurs et modules");
    }
} 