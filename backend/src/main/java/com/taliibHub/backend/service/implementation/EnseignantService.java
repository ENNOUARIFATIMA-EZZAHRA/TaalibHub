package com.taliibHub.backend.service.implementation;

import com.taliibHub.backend.dto.EnseignantDTO;
import com.taliibHub.backend.mapper.EnseignantMapper;
import com.taliibHub.backend.model.Enseignant;
import com.taliibHub.backend.repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;

    public List<EnseignantDTO> getAll() {
        return enseignantRepository.findAll().stream()
                .map(EnseignantMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EnseignantDTO getById(String id) {
        return enseignantRepository.findById(id)
                .map(EnseignantMapper::toDTO)
                .orElse(null);
    }

    public EnseignantDTO create(EnseignantDTO dto) {
        Enseignant enseignant = new Enseignant();
        enseignant.setId(UUID.randomUUID().toString());
        enseignant.setNom(dto.getNom());
        enseignant.setPrenom(dto.getPrenom());
        enseignant.setEmail(dto.getEmail());
        enseignant.setSpecialite(dto.getSpecialite());
        return EnseignantMapper.toDTO(enseignantRepository.save(enseignant));
    }

    public EnseignantDTO update(String id, EnseignantDTO dto) {
        return enseignantRepository.findById(id).map(existing -> {
            existing.setNom(dto.getNom());
            existing.setPrenom(dto.getPrenom());
            existing.setEmail(dto.getEmail());
            existing.setSpecialite(dto.getSpecialite());
            return EnseignantMapper.toDTO(enseignantRepository.save(existing));
        }).orElse(null);
    }

    public boolean delete(String id) {
        if (!enseignantRepository.existsById(id)) return false;
        enseignantRepository.deleteById(id);
        return true;
    }
}
