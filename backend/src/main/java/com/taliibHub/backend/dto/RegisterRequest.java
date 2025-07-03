package com.taliibHub.backend.dto;

import com.taliibHub.backend.enums.RoleUtilisateur;

public class RegisterRequest {
    private String nom;
    private String email;
    private String password;
    private RoleUtilisateur role;
    private String specialite;
    private String prenom;

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    // For backward compatibility
    public String getMotDePass() { return password; }
    public void setMotDePass(String motDePass) { this.password = motDePass; }

    public RoleUtilisateur getRole() { return role; }
    public void setRole(RoleUtilisateur role) { this.role = role; }

    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
}
