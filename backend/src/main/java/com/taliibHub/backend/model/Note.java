package com.taliibHub.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Note {
    @Id
    @GeneratedValue
    private Long id;

    private float valeur;
    private String typeEvaluation; 
    private LocalDate date;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private Cours cours;

    public Long getId() { return id; }
    public float getValeur() { return valeur; }
    public String getTypeEvaluation() { return typeEvaluation; }
    public LocalDate getDate() { return date; }
    public Etudiant getEtudiant() { return etudiant; }
    public Cours getCours() { return cours; }
}
