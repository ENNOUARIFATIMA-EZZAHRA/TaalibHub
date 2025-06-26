package com.taliibHub.backend.dto;

public class RegisterRequest {
    private String nom;
    private String prenom;
    private String email;
    private String motDePass;
    private com.taliibHub.backend.enums.RoleUtilisateur role;

    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getMotDePass() { return motDePass; }
    public com.taliibHub.backend.enums.RoleUtilisateur getRole() { return role; }
}
