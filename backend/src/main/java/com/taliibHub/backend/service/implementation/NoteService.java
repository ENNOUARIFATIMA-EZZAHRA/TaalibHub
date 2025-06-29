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
        List<Note> notes = noteRepository.findByEtudiantNum(etudiantNum);
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
        return result;
    }
}
