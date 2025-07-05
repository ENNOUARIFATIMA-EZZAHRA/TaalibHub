package com.taliibHub.backend.dto;

import com.taliibHub.backend.enums.RoleUtilisateur;

public class UtilisateurDTO {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private RoleUtilisateur role;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public RoleUtilisateur getRole() { return role; }
    public void setRole(RoleUtilisateur role) { this.role = role; }
} 