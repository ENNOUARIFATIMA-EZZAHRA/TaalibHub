package com.taliibHub.backend.repository;

import com.taliibHub.backend.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
    Etudiant findByEmail(String email);
    Etudiant findByNum(int num);
}
