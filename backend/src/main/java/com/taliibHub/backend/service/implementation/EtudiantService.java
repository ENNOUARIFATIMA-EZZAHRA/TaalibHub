package com.taliibHub.backend.service.implementation;

import com.taliibHub.backend.dto.EtudiantRequest;
import com.taliibHub.backend.model.Etudiant;
import com.taliibHub.backend.repository.EtudiantRepository;
import com.taliibHub.backend.enums.RoleUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {
    
    @Autowired
    private EtudiantRepository etudiantRepository;
    
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }
    
    public Optional<Etudiant> getEtudiantById(String id) {
        return etudiantRepository.findById(id);
    }
    
    public Etudiant getEtudiantByEmail(String email) {
        return etudiantRepository.findByEmail(email);
    }
    
    public Etudiant getEtudiantByNum(int num) {
        return etudiantRepository.findByNum(num);
    }
    
    public Etudiant createEtudiant(EtudiantRequest request) {
        EtudiantRequest.EtudiantData etudiantData = request.getEtudiant();
        
        // Create new Etudiant
        Etudiant etudiant = new Etudiant();
        etudiant.setId(etudiantData.getId());
        etudiant.setNom(etudiantData.getNom());
        etudiant.setPrenom(etudiantData.getPrenom());
        etudiant.setEmail(etudiantData.getEmail());
        etudiant.setMotDePass(etudiantData.getMotDePass());
        // Force role to be ETUDIANT for etudiant creation
        etudiant.setRole(RoleUtilisateur.ETUDIANT);
        
        // Use values from request or set defaults if not provided
        if (etudiantData.getNum() > 0) {
            etudiant.setNum(etudiantData.getNum());
        } else {
            etudiant.setNum(generateNextNum());
        }
        
        if (etudiantData.getMatricule() != null && !etudiantData.getMatricule().isEmpty()) {
            etudiant.setMatricule(etudiantData.getMatricule());
        } else {
            etudiant.setMatricule("MAT-" + System.currentTimeMillis());
        }
        
        if (etudiantData.getDateInscription() != null) {
            etudiant.setDateInscription(etudiantData.getDateInscription());
        } else {
            etudiant.setDateInscription(new Date());
        }
        
        return etudiantRepository.save(etudiant);
    }
    
    public Etudiant updateEtudiant(String id, EtudiantRequest request) {
        Optional<Etudiant> existingEtudiant = etudiantRepository.findById(id);
        if (existingEtudiant.isPresent()) {
            Etudiant etudiant = existingEtudiant.get();
            EtudiantRequest.EtudiantData etudiantData = request.getEtudiant();
            
            etudiant.setNom(etudiantData.getNom());
            etudiant.setPrenom(etudiantData.getPrenom());
            etudiant.setEmail(etudiantData.getEmail());
            etudiant.setMotDePass(etudiantData.getMotDePass());
            etudiant.setRole(etudiantData.getRole());
            
            // Update etudiant-specific fields if provided
            if (etudiantData.getNum() > 0) {
                etudiant.setNum(etudiantData.getNum());
            }
            
            if (etudiantData.getMatricule() != null && !etudiantData.getMatricule().isEmpty()) {
                etudiant.setMatricule(etudiantData.getMatricule());
            }
            
            if (etudiantData.getDateInscription() != null) {
                etudiant.setDateInscription(etudiantData.getDateInscription());
            }
            
            return etudiantRepository.save(etudiant);
        }
        return null;
    }
    
    public boolean deleteEtudiant(String id) {
        if (etudiantRepository.existsById(id)) {
            etudiantRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private int generateNextNum() {
        List<Etudiant> allEtudiants = etudiantRepository.findAll();
        if (allEtudiants.isEmpty()) {
            return 1;
        }
        return allEtudiants.stream()
                .mapToInt(Etudiant::getNum)
                .max()
                .orElse(0) + 1;
    }
}
