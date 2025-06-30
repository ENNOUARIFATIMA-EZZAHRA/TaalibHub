package com.taliibHub.backend.dto;

import com.taliibHub.backend.enums.RoleUtilisateur;
import java.util.List;
import java.util.Date;

public class EtudiantRequest {
    private EtudiantData etudiant;
    private List<String> cours;

    public static class EtudiantData {
        private String id;
        private String nom;
        private String prenom;
        private String email;
        private String motDePass;
        private RoleUtilisateur role;
        private int num;
        private String matricule;
        private Date dateInscription;


        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        
        public String getNom() { return nom; }
        public void setNom(String nom) { this.nom = nom; }
        
        public String getPrenom() { return prenom; }
        public void setPrenom(String prenom) { this.prenom = prenom; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getMotDePass() { return motDePass; }
        public void setMotDePass(String motDePass) { this.motDePass = motDePass; }
        
        public RoleUtilisateur getRole() { return role; }
        public void setRole(RoleUtilisateur role) { this.role = role; }
        
        public int getNum() { return num; }
        public void setNum(int num) { this.num = num; }
        
        public String getMatricule() { return matricule; }
        public void setMatricule(String matricule) { this.matricule = matricule; }
        
        public Date getDateInscription() { return dateInscription; }
        public void setDateInscription(Date dateInscription) { this.dateInscription = dateInscription; }
    }


    public EtudiantData getEtudiant() { return etudiant; }
    public void setEtudiant(EtudiantData etudiant) { this.etudiant = etudiant; }
    
    public List<String> getCours() { return cours; }
    public void setCours(List<String> cours) { this.cours = cours; }
} 