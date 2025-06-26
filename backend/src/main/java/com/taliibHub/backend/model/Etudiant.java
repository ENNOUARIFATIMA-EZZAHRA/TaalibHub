package com.taliibHub.backend.model;
import jakarta.persistence.Entity;

import java.util.Date;


@Entity
public class Etudiant extends Utilisateur {
    private int num;
    private String name;
    private String matricule;
    private Date dateInscription;
}


