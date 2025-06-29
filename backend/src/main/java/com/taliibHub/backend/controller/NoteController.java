package com.taliibHub.backend.controller;

import com.taliibHub.backend.dto.NoteDetailsDTO;
import com.taliibHub.backend.service.implementation.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/{etudiantNum}")
    public List<NoteDetailsDTO> getNotes(@PathVariable int etudiantNum) {
        return noteService.getNotesForEtudiant(etudiantNum);
    }
}
