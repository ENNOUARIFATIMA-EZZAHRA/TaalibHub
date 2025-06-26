package com.taliibHub.backend.controller;

import com.taliibHub.backend.model.Cours;
import com.taliibHub.backend.repository.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/etudiant")
public class EtudiantController {
    @Autowired
    private CoursRepository coursRepository;

    @GetMapping("/cours")
    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }
}
