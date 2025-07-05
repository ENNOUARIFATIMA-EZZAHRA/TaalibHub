package com.taliibHub.backend.dto;

import java.util.Date;

public class NoteDTO {
    private Long id;
    private Double valeur;
    private String type;
    private Date date;
    private String etudiantId;
    private String coursId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getValeur() { return valeur; }
    public void setValeur(Double valeur) { this.valeur = valeur; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public String getEtudiantId() { return etudiantId; }
    public void setEtudiantId(String etudiantId) { this.etudiantId = etudiantId; }
    public String getCoursId() { return coursId; }
    public void setCoursId(String coursId) { this.coursId = coursId; }
} 