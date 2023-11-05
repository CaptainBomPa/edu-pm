package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.TeamDTO;
import com.edu.pm.backend.service.entity.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
@CrossOrigin(origins = "${pm.cross-origin}")
public class TeamController {

    private final TeamService teamService;

    @GetMapping(value = "/team/all")
    public ResponseEntity<Collection<TeamDTO>> getAll() {
        return ResponseEntity.ok(teamService.findAll());
    }
}
