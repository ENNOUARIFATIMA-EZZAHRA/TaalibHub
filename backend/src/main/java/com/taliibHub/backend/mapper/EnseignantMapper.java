package com.taliibHub.backend.mapper;
import com.taliibHub.backend.model.Enseignant;
import com.taliibHub.backend.dto.EnseignantDTO;

public class EnseignantMapper {
    public static EnseignantDTO toDTO(Enseignant enseignant) {
        EnseignantDTO dto = new EnseignantDTO();
        dto.setId(enseignant.getId());
        dto.setNom(enseignant.getNom());
        dto.setPrenom(enseignant.getPrenom());
        dto.setEmail(enseignant.getEmail());
        dto.setSpecialite(enseignant.getSpecialite());
        return dto;
    }
} 