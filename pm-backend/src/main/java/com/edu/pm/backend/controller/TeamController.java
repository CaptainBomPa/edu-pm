package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.IdentityDTO;
import com.edu.pm.backend.commons.dto.TeamDTO;
import com.edu.pm.backend.service.TeamService;
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
public class TeamController {

    private final TeamService teamService;

    @GetMapping(value = "/team/get-all")
    public ResponseEntity<Collection<TeamDTO>> getAll() {
        return ResponseEntity.ok(teamService.findAll());
    }

    @PostMapping(value = "/team/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TeamDTO> update(@RequestBody TeamDTO dto) {
        if (dto.getId() != null) {
            return ResponseEntity.ok(teamService.update(dto));
        }
        return ResponseEntity.ok(teamService.add(dto));
    }

    @PostMapping(value = "/team/delete", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TeamDTO> delete(@RequestBody IdentityDTO dto) {
        return ResponseEntity.ok(teamService.remove(dto.getId()));
    }

    @PostMapping(value = "/team/getById", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TeamDTO> getById(@RequestBody IdentityDTO dto) {
        return ResponseEntity.ok(teamService.findByIdDTO(dto.getId()));
    }
}
