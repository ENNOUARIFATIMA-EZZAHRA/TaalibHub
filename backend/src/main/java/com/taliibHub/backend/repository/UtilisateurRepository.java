package com.taliibHub.backend.repository;

import com.taliibHub.backend.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
    Utilisateur findByEmail(String email);
}
