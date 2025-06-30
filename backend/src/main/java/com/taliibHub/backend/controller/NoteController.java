package com.taliibHub.backend.controller;

import com.taliibHub.backend.dto.NoteDetailsDTO;
import com.taliibHub.backend.model.Utilisateur;
import com.taliibHub.backend.service.implementation.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/{etudiantNum}")
    public ResponseEntity<List<NoteDetailsDTO>> getNotes(@PathVariable String etudiantNum, Authentication authentication) {
        System.out.println("Requête reçue pour les notes de l'étudiant: " + etudiantNum);
        System.out.println("NoteController - getNotes called for student: " + etudiantNum);
        
        if (authentication == null || authentication.getPrincipal() == null) {
            System.out.println("NoteController - No authentication found");
            return ResponseEntity.status(401).build();
        }
        
        Utilisateur user = (Utilisateur) authentication.getPrincipal();
        System.out.println("NoteController - User authenticated: " + user.getEmail());
        
        try {

            List<NoteDetailsDTO> notes;
            
            try {
                int studentNum = Integer.parseInt(etudiantNum);
                System.out.println("NoteController - Using numeric student number: " + studentNum);
                notes = noteService.getNotesForEtudiant(studentNum);
            } catch (NumberFormatException e) {

                System.out.println("NoteController - Using UUID method for student: " + etudiantNum);
                notes = noteService.getNotesForEtudiantByUUID(etudiantNum);
            }
            
            System.out.println("NoteController - Found " + notes.size() + " notes for student " + etudiantNum);
            return ResponseEntity.ok(notes);
        } catch (Exception e) {
            System.out.println("NoteController - Error fetching notes: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}
