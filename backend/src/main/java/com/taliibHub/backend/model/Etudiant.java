package com.taliibHub.backend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.util.Date;
import com.taliibHub.backend.enums.RoleUtilisateur;

@Entity
public class Etudiant extends Utilisateur {
    private int num;
    private String matricule;
    private Date dateInscription;

    // Constructors
    public Etudiant() {
        super();
    }

    public Etudiant(String id, String nom, String prenom, String email, String motDePass, RoleUtilisateur role, 
                   int num, String matricule, Date dateInscription) {
        super();
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setEmail(email);
        setMotDePass(motDePass);
        setRole(role);
        this.num = num;
        this.matricule = matricule;
        this.dateInscription = dateInscription;
    }

    // Getters and Setters
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id='" + getId() + '\'' +
                ", nom='" + getNom() + '\'' +
                ", prenom='" + getPrenom() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role=" + getRole() +
                ", num=" + num +
                ", matricule='" + matricule + '\'' +
                ", dateInscription=" + dateInscription +
                '}';
    }
}


