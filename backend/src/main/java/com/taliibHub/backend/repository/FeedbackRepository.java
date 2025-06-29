package com.taliibHub.backend.repository;

import com.taliibHub.backend.model.Feedback;
import com.taliibHub.backend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Feedback findByNote(Note note);
}
