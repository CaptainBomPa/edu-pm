package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.IdentityDTO;
import com.edu.pm.backend.commons.dto.ProjectDTO;
import com.edu.pm.backend.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping(value = "/project/get-all")
    public ResponseEntity<Collection<ProjectDTO>> getAll() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @PostMapping(value = "/project/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProjectDTO> update(@RequestBody ProjectDTO dto) {
        if (dto.getId() != null) {
            return ResponseEntity.ok(projectService.update(dto));
        }
        return ResponseEntity.ok(projectService.add(dto));
    }

    @PostMapping(value = "/project/delete", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProjectDTO> delete(@RequestBody IdentityDTO dto) {
        return ResponseEntity.ok(projectService.remove(dto.getId()));
    }

}
