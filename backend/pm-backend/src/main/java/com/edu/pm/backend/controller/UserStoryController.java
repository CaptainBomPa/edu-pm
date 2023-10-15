package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.IdentityDTO;
import com.edu.pm.backend.commons.dto.UserStoryDTO;
import com.edu.pm.backend.service.UserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
@CrossOrigin(origins = "${pm.cross-origin}")
public class UserStoryController {

    private final UserStoryService userStoryService;

    @GetMapping(value = "/userStory/get-all")
    public ResponseEntity<Collection<UserStoryDTO>> getAll() {
        return ResponseEntity.ok(userStoryService.findAll());
    }

    @DeleteMapping(value = "/userStory/delete/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserStoryDTO> delete(@PathVariable("id") Integer userStoryId) {
        return ResponseEntity.ok(userStoryService.remove(userStoryId));
    }

    @PostMapping(value = "/userStory/deleteMultiple", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Collection<UserStoryDTO>> deleteMultiple(@RequestBody List<Integer> ids) {
        return ResponseEntity.ok(userStoryService.removeMultiple(ids));
    }

    @PostMapping(value = "/userStory/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserStoryDTO> update(@RequestBody UserStoryDTO dto) {
        return ResponseEntity.ok(userStoryService.update(dto));
    }

    @PutMapping(value = "/userStory/add", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserStoryDTO> add(@RequestBody UserStoryDTO dto) {
        return ResponseEntity.ok(userStoryService.add(dto));
    }

    @PostMapping(value = "/userStory/getById", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserStoryDTO> getById(@RequestBody IdentityDTO dto) {
        return ResponseEntity.ok(userStoryService.findByIdDTO(dto.getId()));
    }
}
