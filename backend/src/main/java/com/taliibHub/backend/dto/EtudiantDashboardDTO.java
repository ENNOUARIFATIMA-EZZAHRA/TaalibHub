package com.taliibHub.backend.dto;

import com.taliibHub.backend.model.Etudiant;
import com.taliibHub.backend.model.Cours;
import com.taliibHub.backend.model.Note;
import com.taliibHub.backend.model.Feedback;
import java.util.List;

public class EtudiantDashboardDTO {
    public Etudiant etudiant;
    public List<Cours> cours;
    public float moyenneGenerale;
    public int tauxPresence;
    public int modulesSuivis;
    public int devoirsEnAttente;
    public List<Note> dernieresNotes;
    public List<Cours> prochainsCours;
    public List<Feedback> messagesEnseignants;
} 