package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.UserDTO;
import com.edu.pm.backend.commons.dto.auth.ChangePasswordDTO;
import com.edu.pm.backend.commons.dto.auth.RegisterRequest;
import com.edu.pm.backend.service.entity.UserService;
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

    @CrossOrigin(origins = "${pm.cross-origin}")
    @GetMapping(value = "/user/currentUserInfo")
    public ResponseEntity<UserDTO> getCurrentUserInfo(Authentication authentication) {
        return ResponseEntity.ok(userService.getCurrentUser(authentication.getName()));
    }

    @PutMapping(value = "/user/updateUserInfo")
    public ResponseEntity<UserDTO> updateUserInfo(@RequestBody UserDTO userDTO, Authentication authentication) {
        return ResponseEntity.ok(userService.updateUser(userDTO));
    }

    @PutMapping(value = "/user/updateFullUserInfo")
    public ResponseEntity<UserDTO> updateFullUserInfo(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateFullUser(userDTO));
    }

    @GetMapping(value = "/user/all")
    public ResponseEntity<Collection<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(value = "/user/allWithAvatars")
    public ResponseEntity<Collection<UserDTO>> getAllWithAvatars() {
        return ResponseEntity.ok(userService.getAllWithAvatars());
    }

    @GetMapping(value = "/user/allBlocked")
    public ResponseEntity<Collection<UserDTO>> getAllBlocked() {
        return ResponseEntity.ok(userService.getAllBlocked());
    }

    @PutMapping(value = "/user/unlockAccounts")
    public ResponseEntity<Collection<UserDTO>> unlockAccounts(@RequestBody Collection<UserDTO> users) {
        return ResponseEntity.ok(userService.unlockUsers(users));
    }

    @DeleteMapping(value = "/user/removeAccounts")
    public ResponseEntity<Collection<UserDTO>> removeAccounts(@RequestBody Collection<UserDTO> users) {
        return ResponseEntity.ok(userService.removeAccounts(users));
    }

    @PostMapping(value = "/user/add", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO> update(@RequestBody RegisterRequest registerRequest) {
        userService.addUser(registerRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/user/changePassword", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO> changePassword(@RequestBody ChangePasswordDTO dto, Authentication authentication) {
        userService.changePassword(dto, authentication.getName());
        return ResponseEntity.ok().build();
    }
}
