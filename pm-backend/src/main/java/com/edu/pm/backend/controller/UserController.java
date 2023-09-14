package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.UserDTO;
import com.edu.pm.backend.commons.dto.auth.ChangePasswordDTO;
import com.edu.pm.backend.commons.dto.auth.UpdateUserDTO;
import com.edu.pm.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
@CrossOrigin(origins = "${pm.cross-origin}")
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

    @PostMapping(value = "/user/remove", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO> remove(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.removeUser(dto));
    }

    @PostMapping(value = "/user/changePassword", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO> changePassword(@RequestBody ChangePasswordDTO dto, Authentication authentication) {
        userService.changePassword(dto, authentication.getName());
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/user/addRole", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO> addRole(@RequestBody UpdateUserDTO dto) {
        return ResponseEntity.ok(userService.addRole(dto.getId(), dto.getRole()));
    }

    @PostMapping(value = "/user/removeRole", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO> removeRole(@RequestBody UpdateUserDTO dto) {
        return ResponseEntity.ok(userService.removeRole(dto.getId(), dto.getRole()));
    }

    @PostMapping(value = "/user/updateTeam", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO> updateTeam(@RequestBody UpdateUserDTO dto) {
        return ResponseEntity.ok(userService.updateTeam(dto.getId(), dto.getTeam()));
    }

    @PostMapping(value = "/user/addProject", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO> addProject(@RequestBody UpdateUserDTO dto) {
        return ResponseEntity.ok(userService.addProject(dto.getId(), dto.getProjectId()));
    }

    @PostMapping(value = "/user/removeProject", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO> removeProject(@RequestBody UpdateUserDTO dto) {
        return ResponseEntity.ok(userService.removeProject(dto.getId(), dto.getProjectId()));
    }
}
