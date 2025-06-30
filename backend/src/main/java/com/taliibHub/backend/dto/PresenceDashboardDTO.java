package com.taliibHub.backend.dto;

import java.util.List;

public class PresenceDashboardDTO {
    public float tauxPresence;
    public int nbPresences;
    public int nbRetards;
    public int nbAbsences;
    public List<PresenceInfo> historique;

    public static class PresenceInfo {
        public String cours;
        public String date;
        public String heure;
        public String statut; // "Présent", "En retard", "Absent"
    }
} 