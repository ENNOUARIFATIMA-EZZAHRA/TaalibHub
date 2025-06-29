package com.taliibHub.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Feedback {
    @Id
    @GeneratedValue
    private Long id;

    private String contenu;
    private Date date;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private Cours cours;

    @ManyToOne
    private Note note;

    public String getContenu() { return contenu; }

    public Note getNote() { return note; }
}
