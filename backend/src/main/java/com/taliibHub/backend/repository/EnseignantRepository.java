package com.taliibHub.backend.repository;

import com.taliibHub.backend.model.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepository extends JpaRepository<Enseignant, String> {
}
