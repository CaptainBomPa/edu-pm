package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.IdentityDTO;
import com.edu.pm.backend.commons.dto.TaskDTO;
import com.edu.pm.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
@CrossOrigin(origins = "${pm.cross-origin}")
public class TaskController {

    private final TaskService taskService;

    @GetMapping(value = "/task/get-all")
    public ResponseEntity<Collection<TaskDTO>> getAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @PostMapping(value = "/task/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskDTO> update(@RequestBody TaskDTO dto) {
        if (dto.getId() != null) {
            return ResponseEntity.ok(taskService.update(dto));
        }
        return ResponseEntity.ok(taskService.add(dto));
    }

    @DeleteMapping(value = "/task/delete/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskDTO> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(taskService.remove(id));
    }

    @PostMapping(value = "/task/getById", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TaskDTO> getById(@RequestBody IdentityDTO dto) {
        return ResponseEntity.ok(taskService.remove(dto.getId()));
    }
}
