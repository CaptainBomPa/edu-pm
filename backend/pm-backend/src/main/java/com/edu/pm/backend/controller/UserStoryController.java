package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.UserStoryDTO;
import com.edu.pm.backend.service.entity.UserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class UserStoryController {

    private final UserStoryService userStoryService;

    @DeleteMapping(value = "/userStory/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserStoryDTO> delete(@PathVariable("id") Integer userStoryId) {
        return ResponseEntity.ok(userStoryService.remove(userStoryId));
    }

    @PostMapping(value = "/userStory/deleteMultiple", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Collection<UserStoryDTO>> deleteMultiple(@RequestBody List<Integer> ids) {
        return ResponseEntity.ok(userStoryService.removeMultiple(ids));
    }

    @PutMapping(value = "/userStory/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserStoryDTO> update(@RequestBody UserStoryDTO dto) {
        return ResponseEntity.ok(userStoryService.update(dto));
    }

    @PostMapping(value = "/userStory/add", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserStoryDTO> add(@RequestBody UserStoryDTO dto) {
        return ResponseEntity.ok(userStoryService.add(dto));
    }
}
