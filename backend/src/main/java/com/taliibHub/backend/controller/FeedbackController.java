package com.taliibHub.backend.controller;

import com.taliibHub.backend.model.Feedback;
import com.taliibHub.backend.model.Utilisateur;
import com.taliibHub.backend.repository.FeedbackRepository;
import com.taliibHub.backend.repository.NoteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
@Tag(name = "Feedback", description = "Opérations de gestion des feedbacks étudiants/enseignants")
public class FeedbackController {
    
    @Autowired
    private FeedbackRepository feedbackRepository;
    
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/{etudiantId}")
    @Operation(summary = "Récupérer les feedbacks d'un étudiant", description = "Retourne la liste des feedbacks associés à un étudiant donné (par ID ou numéro)")
    public ResponseEntity<List<Feedback>> getFeedbackForStudent(@PathVariable String etudiantId) {
        System.out.println("Requête reçue pour les feedbacks de l'étudiant: " + etudiantId);
        System.out.println("FeedbackController - getFeedbackForStudent called for student: " + etudiantId);
        try {
            List<Feedback> feedbacks;
            try {

                int studentNum = Integer.parseInt(etudiantId);
                System.out.println("FeedbackController - Using numeric student number: " + studentNum);
                feedbacks = getFeedbackByStudentNum(studentNum);
            } catch (NumberFormatException e) {

                System.out.println("FeedbackController - Using UUID method for student: " + etudiantId);
                feedbacks = getFeedbackByStudentUUID(etudiantId);
            }
            System.out.println("FeedbackController - Found " + feedbacks.size() + " feedbacks for student " + etudiantId);
            return ResponseEntity.ok(feedbacks);
        } catch (Exception e) {
            System.out.println("FeedbackController - Error fetching feedbacks: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
    
    private List<Feedback> getFeedbackByStudentNum(int studentNum) {

        return noteRepository.findByEtudiantNum(studentNum)
            .stream()
            .map(note -> feedbackRepository.findByNote(note))
            .filter(feedback -> feedback != null)
            .collect(Collectors.toList());
    }
    
    private List<Feedback> getFeedbackByStudentUUID(String studentUUID) {
        return noteRepository.findByEtudiantId(studentUUID)
            .stream()
            .map(note -> feedbackRepository.findByNote(note))
            .filter(feedback -> feedback != null)
            .collect(Collectors.toList());
    }
}
