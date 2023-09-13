package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.UserDTO;
import com.edu.pm.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/user/get-all")
    public ResponseEntity<Collection<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping(value = "/user/add", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.addUser(dto));
    }
}
