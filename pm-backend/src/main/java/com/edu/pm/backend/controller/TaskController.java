package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.IdentityDTO;
import com.edu.pm.backend.commons.dto.TaskDTO;
import com.edu.pm.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping(value = "/task/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskDTO> update(@RequestBody TaskDTO dto) {
        if (dto.getId() != null) {
            return ResponseEntity.ok(taskService.update(dto));
        }
        return ResponseEntity.ok(taskService.add(dto));
    }

    @PostMapping(value = "/task/delete", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskDTO> delete(@RequestBody IdentityDTO dto) {
        return ResponseEntity.ok(taskService.remove(dto.getId()));
    }
}
