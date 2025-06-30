package com.taliibHub.backend.controller;

import com.taliibHub.backend.dto.PresenceDashboardDTO;
import com.taliibHub.backend.service.implementation.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/presence")
public class PresenceController {
    @Autowired
    private PresenceService presenceService;

    @GetMapping("/etudiant/{id}/dashboard")
    public PresenceDashboardDTO getDashboard(@PathVariable String id) {
        return presenceService.getDashboard(id);
    }
}
