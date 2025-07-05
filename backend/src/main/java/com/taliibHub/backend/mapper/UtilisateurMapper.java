package com.taliibHub.backend.mapper;
import com.taliibHub.backend.model.Utilisateur;
import com.taliibHub.backend.dto.UtilisateurDTO;

public class UtilisateurMapper {
    public static UtilisateurDTO toDTO(Utilisateur user) {
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(user.getId());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
} 