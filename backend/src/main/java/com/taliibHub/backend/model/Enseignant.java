package com.taliibHub.backend.model;

import jakarta.persistence.Entity;

@Entity
public class Enseignant extends Utilisateur {
    private String specialite;

    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }
}

