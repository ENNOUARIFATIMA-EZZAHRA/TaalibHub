package com.taliibHub.backend.mapper;
import com.taliibHub.backend.model.Presence;
import com.taliibHub.backend.dto.PresenceDTO;

public class PresenceMapper {
    public static PresenceDTO toDTO(Presence presence) {
        PresenceDTO dto = new PresenceDTO();
        dto.setId(presence.getId());
        dto.setDate(presence.getDate());
        dto.setStatus(presence.getStatus());
        if (presence.getEtudiant() != null) dto.setEtudiantId(presence.getEtudiant().getId());
        if (presence.getCours() != null) dto.setCoursId(presence.getCours().getId());
        return dto;
    }
} 