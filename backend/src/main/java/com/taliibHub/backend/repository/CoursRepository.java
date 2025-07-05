package com.taliibHub.backend.repository;

import com.taliibHub.backend.model.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursRepository extends JpaRepository<Cours, String> {
}
