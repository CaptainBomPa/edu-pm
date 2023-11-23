package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.TaskAddDTO;
import com.edu.pm.backend.commons.dto.TaskDTO;
import com.edu.pm.backend.service.entity.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class TaskController {

    private final TaskService taskService;

    @PostMapping(value = "/task", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskDTO> add(@RequestBody TaskAddDTO dto) {
        return ResponseEntity.ok(taskService.add(dto));
    }

    @PutMapping(value = "/task", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskDTO> update(@RequestBody TaskAddDTO dto) {
        return ResponseEntity.ok(taskService.update(dto));
    }

    @DeleteMapping(value = "/task/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskDTO> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(taskService.remove(id));
    }
}
