package com.taliibHub.backend.service.implementation;

import com.taliibHub.backend.dto.NoteDetailsDTO;
import com.taliibHub.backend.model.Note;
import com.taliibHub.backend.model.Feedback;
import com.taliibHub.backend.repository.NoteRepository;
import com.taliibHub.backend.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<NoteDetailsDTO> getNotesForEtudiant(int etudiantNum) {
        System.out.println("NoteService - getNotesForEtudiant called for student: " + etudiantNum);
        
        List<Note> notes = noteRepository.findByEtudiantNum(etudiantNum);
        System.out.println("NoteService - Found " + notes.size() + " notes from repository");
        
        List<NoteDetailsDTO> result = new ArrayList<>();
        for (Note note : notes) {
            NoteDetailsDTO dto = new NoteDetailsDTO();
            dto.typeEvaluation = note.getTypeEvaluation();
            dto.coursCode = note.getCours().getCode();
            dto.coursTitre = note.getCours().getTitre();
            dto.coursMatiere = note.getCours().getDescription();
            dto.date = note.getDate().toString();
            dto.valeur = note.getValeur();
            Feedback fb = feedbackRepository.findByNote(note);
            dto.feedback = fb != null ? fb.getContenu() : "";
            result.add(dto);
        }
        
        System.out.println("NoteService - Returning " + result.size() + " note DTOs");
        return result;
    }
    

    public List<NoteDetailsDTO> getNotesForEtudiantByUUID(String etudiantUUID) {
        System.out.println("NoteService - getNotesForEtudiantByUUID called for student UUID: " + etudiantUUID);
        
        List<Note> notes = noteRepository.findByEtudiantId(etudiantUUID);
        System.out.println("NoteService - Found " + notes.size() + " notes from repository for UUID: " + etudiantUUID);
        
        List<NoteDetailsDTO> result = new ArrayList<>();
        for (Note note : notes) {
            NoteDetailsDTO dto = new NoteDetailsDTO();
            dto.typeEvaluation = note.getTypeEvaluation();
            dto.coursCode = note.getCours().getCode();
            dto.coursTitre = note.getCours().getTitre();
            dto.coursMatiere = note.getCours().getDescription();
            dto.date = note.getDate().toString();
            dto.valeur = note.getValeur();
            Feedback fb = feedbackRepository.findByNote(note);
            dto.feedback = fb != null ? fb.getContenu() : "";
            result.add(dto);
        }
        
        System.out.println("NoteService - Returning " + result.size() + " note DTOs for UUID student");
        return result;
    }
}
