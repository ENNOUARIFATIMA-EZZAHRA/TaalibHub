package com.taliibHub.backend.mapper;
import com.taliibHub.backend.model.Etudiant;
import com.taliibHub.backend.dto.EtudiantDTO;

public class EtudiantMapper {
    public static EtudiantDTO toDTO(Etudiant etudiant) {
        EtudiantDTO dto = new EtudiantDTO();
        dto.setId(etudiant.getId());
        dto.setNom(etudiant.getNom());
        dto.setPrenom(etudiant.getPrenom());
        dto.setEmail(etudiant.getEmail());
        return dto;
    }
} 