package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.ProjectDTO;
import com.edu.pm.backend.service.entity.ProjectService;
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
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping(value = "/project/all")
    public ResponseEntity<Collection<ProjectDTO>> getAll() {
        return ResponseEntity.ok(projectService.findAll());
    }
}
