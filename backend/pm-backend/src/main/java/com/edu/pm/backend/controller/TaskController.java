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
@CrossOrigin(origins = "${pm.cross-origin}")
public class TaskController {

    private final TaskService taskService;

    @PostMapping(value = "/task/add", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskDTO> add(@RequestBody TaskAddDTO dto) {
        return ResponseEntity.ok(taskService.add(dto));
    }

    @PutMapping(value = "/task/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskDTO> update(@RequestBody TaskAddDTO dto) {
        return ResponseEntity.ok(taskService.update(dto));
    }

    @DeleteMapping(value = "/task/delete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskDTO> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(taskService.remove(id));
    }
}
