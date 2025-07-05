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

    @ManyToOne
    private Enseignant enseignant;

    public Long getId() { return id; }
    public String getContenu() { return contenu; }
    public Date getDate() { return date; }
    public Etudiant getEtudiant() { return etudiant; }
    public Cours getCours() { return cours; }
    public Note getNote() { return note; }
    public Enseignant getEnseignant() { return enseignant; }

    public void setId(Long id) { this.id = id; }
    public void setContenu(String contenu) { this.contenu = contenu; }
    public void setDate(Date date) { this.date = date; }
    public void setEtudiant(Etudiant etudiant) { this.etudiant = etudiant; }
    public void setCours(Cours cours) { this.cours = cours; }
    public void setNote(Note note) { this.note = note; }
    public void setEnseignant(Enseignant enseignant) { this.enseignant = enseignant; }
}
