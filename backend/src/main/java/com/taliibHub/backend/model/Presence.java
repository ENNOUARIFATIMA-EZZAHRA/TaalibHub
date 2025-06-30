package com.taliibHub.backend.model;

import com.taliibHub.backend.enums.Status;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Presence {

    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private Cours cours;

    private String heure;

    public Cours getCours() { return cours; }
    public Date getDate() { return date; }
    public Status getStatus() { return status; }
    public String getHeure() { return heure; }
}
