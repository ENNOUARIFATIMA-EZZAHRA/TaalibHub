package com.taliibHub.backend.controller;

import com.taliibHub.backend.dto.EnseignantDTO;
import com.taliibHub.backend.service.implementation.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enseignants")
@CrossOrigin(origins = {"http://localhost:4200"})
public class EnseignantController {

    @Autowired
    private EnseignantService enseignantService;

    @GetMapping
    public ResponseEntity<List<EnseignantDTO>> getAll() {
        return ResponseEntity.ok(enseignantService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnseignantDTO> getById(@PathVariable String id) {
        EnseignantDTO dto = enseignantService.getById(id);
        return (dto != null) ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EnseignantDTO> create(@RequestBody EnseignantDTO dto) {
        return ResponseEntity.ok(enseignantService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnseignantDTO> update(@PathVariable String id, @RequestBody EnseignantDTO dto) {
        EnseignantDTO updated = enseignantService.update(id, dto);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return enseignantService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
