package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.IdentityDTO;
import com.edu.pm.backend.commons.dto.UserStoryDTO;
import com.edu.pm.backend.service.UserStoryService;
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
public class UserStoryController {

    private final UserStoryService userStoryService;

    @GetMapping(value = "/userStory/get-all")
    public ResponseEntity<Collection<UserStoryDTO>> getAll() {
        return ResponseEntity.ok(userStoryService.findAll());
    }

    @PostMapping(value = "/userStory/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserStoryDTO> update(@RequestBody UserStoryDTO dto) {
        if (dto.getId() != null) {
            return ResponseEntity.ok(userStoryService.update(dto));
        }
        return ResponseEntity.ok(userStoryService.add(dto));
    }

    @PostMapping(value = "/userStory/delete", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserStoryDTO> delete(@RequestBody IdentityDTO dto) {
        return ResponseEntity.ok(userStoryService.remove(dto.getId()));
    }
}
