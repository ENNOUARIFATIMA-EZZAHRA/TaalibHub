package com.taliibHub.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Cours {
    @Id
    private String code;
    private String titre;
    private String semester;
    private String description;

    @ManyToOne
    private Enseignant enseignant;

    @OneToMany(mappedBy = "cours")
    private List<Presence> presences;

    public String getId() { return code; }
    public void setId(String code) { this.code = code; }
    public String getTitre() { return titre; }
    public String getSemester() { return semester; }
    public Enseignant getEnseignant() { return enseignant; }
    public List<Presence> getPresences() { return presences; }
    public String getDescription() { return description; }
    public String getNom() { return titre; }
    public String getCode() { return code; }
}

