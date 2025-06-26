package com.taliibHub.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import com.taliibHub.backend.enums.RoleUtilisateur;
import jakarta.persistence.Column;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {
    @Id
    private String id;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String email;
    private String motDePass;
    private RoleUtilisateur role;

    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getMotDePass() { return motDePass; }
    public RoleUtilisateur getRole() { return role; }
    public void setId(String id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setMotDePass(String motDePass) { this.motDePass = motDePass; }
    public void setRole(RoleUtilisateur role) { this.role = role; }
}
