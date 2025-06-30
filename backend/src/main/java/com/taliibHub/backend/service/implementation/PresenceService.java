package com.taliibHub.backend.service.implementation;

import com.taliibHub.backend.dto.PresenceDashboardDTO;
import com.taliibHub.backend.model.Presence;
import com.taliibHub.backend.repository.PresenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PresenceService {
    @Autowired
    private PresenceRepository presenceRepository;

    public PresenceDashboardDTO getDashboard(String etudiantId) {
        List<Presence> presences = presenceRepository.findByEtudiantId(etudiantId);
        PresenceDashboardDTO dto = new PresenceDashboardDTO();
        int total = presences.size();
        int presents = 0, retards = 0, absents = 0;
        List<PresenceDashboardDTO.PresenceInfo> historique = new ArrayList<>();
        for (Presence p : presences) {
            PresenceDashboardDTO.PresenceInfo info = new PresenceDashboardDTO.PresenceInfo();
            info.cours = p.getCours() != null ? p.getCours().getNom() : "";
            info.date = p.getDate() != null ? p.getDate().toString() : "";
            info.heure = p.getHeure() != null ? p.getHeure().toString() : "";
            info.statut = p.getStatus() != null ? p.getStatus().toString() : "";
            historique.add(info);
            if ("PRESENT".equalsIgnoreCase(info.statut)) presents++;
            else if ("RETARD".equalsIgnoreCase(info.statut)) retards++;
            else if ("ABSENT".equalsIgnoreCase(info.statut)) absents++;
        }
        dto.tauxPresence = total == 0 ? 0 : (presents * 100f) / total;
        dto.nbPresences = presents;
        dto.nbRetards = retards;
        dto.nbAbsences = absents;
        dto.historique = historique;
        return dto;
    }
}
