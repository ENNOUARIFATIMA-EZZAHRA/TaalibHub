package com.taliibHub.backend.dto;

import java.util.Date;

public class FeedbackDTO {
    private Long id;
    private String contenu;
    private Date date;
    private String etudiantId;
    private String enseignantId;
    private String coursId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public String getEtudiantId() { return etudiantId; }
    public void setEtudiantId(String etudiantId) { this.etudiantId = etudiantId; }
    public String getEnseignantId() { return enseignantId; }
    public void setEnseignantId(String enseignantId) { this.enseignantId = enseignantId; }
    public String getCoursId() { return coursId; }
    public void setCoursId(String coursId) { this.coursId = coursId; }
} 