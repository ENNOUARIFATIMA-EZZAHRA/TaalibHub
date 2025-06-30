package com.taliibHub.backend.repository;

import com.taliibHub.backend.model.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PresenceRepository extends JpaRepository<Presence, Integer> {
    List<Presence> findByEtudiantId(String etudiantId);
}
