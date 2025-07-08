package com.taliibHub.backend.mapper;
import com.taliibHub.backend.model.Note;
import com.taliibHub.backend.dto.NoteDTO;

public class NoteMapper {
    public static NoteDTO toDTO(Note note) {
        NoteDTO dto = new NoteDTO();
        dto.setId(note.getId());
        dto.setValeur(Double.valueOf(note.getValeur()));
        dto.setType(note.getTypeEvaluation());
        if (note.getDate() != null) {
            dto.setDate(java.sql.Date.valueOf(note.getDate()));
        }
        if (note.getEtudiant() != null) dto.setEtudiantId(note.getEtudiant().getId());
        if (note.getCours() != null) dto.setCoursId(note.getCours().getId());
        return dto;
    }
} 