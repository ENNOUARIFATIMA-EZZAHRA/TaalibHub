package com.taliibHub.backend.mapper;

import com.taliibHub.backend.model.Feedback;
import com.taliibHub.backend.dto.FeedbackDTO;

public class FeedbackMapper {
    public static FeedbackDTO toDTO(Feedback feedback) {
        FeedbackDTO dto = new FeedbackDTO();
        dto.setId(feedback.getId());
        dto.setContenu(feedback.getContenu());
        dto.setDate(feedback.getDate());
        if (feedback.getEtudiant() != null) dto.setEtudiantId(feedback.getEtudiant().getId());
        if (feedback.getEnseignant() != null) dto.setEnseignantId(feedback.getEnseignant().getId());
        if (feedback.getCours() != null) dto.setCoursId(feedback.getCours().getId());
        return dto;
    }
} 