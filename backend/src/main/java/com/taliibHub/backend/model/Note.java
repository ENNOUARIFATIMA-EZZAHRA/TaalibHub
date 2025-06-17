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
    private String typeEvaluation; // TP, examen...
    private LocalDate date;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private Cours cours;
}
