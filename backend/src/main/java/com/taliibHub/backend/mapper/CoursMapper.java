package com.taliibHub.backend.mapper;
import com.taliibHub.backend.model.Cours;
import com.taliibHub.backend.dto.CoursDTO;

public class CoursMapper {
    public static CoursDTO toDTO(Cours cours) {
        CoursDTO dto = new CoursDTO();
        dto.setId(cours.getId());
        dto.setTitre(cours.getTitre());
        dto.setSemester(cours.getSemester());
        dto.setDescription(cours.getDescription());
        if (cours.getEnseignant() != null) dto.setEnseignantId(cours.getEnseignant().getId());
        return dto;
    }
} 