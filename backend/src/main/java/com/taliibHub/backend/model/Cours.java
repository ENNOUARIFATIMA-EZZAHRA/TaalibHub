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

    @ManyToOne
    private Enseignant enseignant;

    @OneToMany(mappedBy = "cours")
    private List<Presence> presences;
}

