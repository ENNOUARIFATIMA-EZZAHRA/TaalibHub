package com.taliibHub.backend.controller;

import com.taliibHub.backend.model.Feedback;
import com.taliibHub.backend.model.Utilisateur;
import com.taliibHub.backend.repository.FeedbackRepository;
import com.taliibHub.backend.repository.NoteRepository;
import com.taliibHub.backend.repository.EtudiantRepository;
import com.taliibHub.backend.repository.EnseignantRepository;
import com.taliibHub.backend.repository.CoursRepository;
import com.taliibHub.backend.dto.FeedbackDTO;
import com.taliibHub.backend.mapper.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class FeedbackController {
    
    @Autowired
    private FeedbackRepository feedbackRepository;
    
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private CoursRepository coursRepository;

    @GetMapping("/{etudiantId}")
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

    @PostMapping
    public FeedbackDTO ajouterFeedback(@RequestBody Feedback feedback) {
        if (feedback.getEtudiant() != null && feedback.getEtudiant().getId() != null) {
            feedback.setEtudiant(etudiantRepository.findById(feedback.getEtudiant().getId()).orElse(null));
        }
        if (feedback.getEnseignant() != null && feedback.getEnseignant().getId() != null) {
            feedback.setEnseignant(enseignantRepository.findById(feedback.getEnseignant().getId()).orElse(null));
        }
        if (feedback.getCours() != null && feedback.getCours().getId() != null) {
            feedback.setCours(coursRepository.findById(feedback.getCours().getId()).orElse(null));
        }
        Feedback saved = feedbackRepository.save(feedback);
        return FeedbackMapper.toDTO(saved);
    }
}
