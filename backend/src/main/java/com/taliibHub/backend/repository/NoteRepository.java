package com.taliibHub.backend.repository;

import com.taliibHub.backend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByEtudiantNum(int num);
    List<Note> findByEtudiantId(String etudiantId);
}
