package com.taliibHub.backend.model;

import ch.qos.logback.core.status.Status;
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
}

