package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.UserDTO;
import com.edu.pm.backend.commons.dto.auth.ChangePasswordDTO;
import com.edu.pm.backend.commons.dto.auth.RegisterRequest;
import com.edu.pm.backend.commons.dto.auth.UpdateUserDTO;
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

    @PostMapping(value = "/user/updateUserInfo")
    public ResponseEntity<UserDTO> updateUserInfo(@RequestBody UserDTO userDTO, Authentication authentication) {
        return ResponseEntity.ok(userService.updateUser(userDTO));
    }

    @PostMapping(value = "/user/updateFullUserInfo")
    public ResponseEntity<UserDTO> updateFullUserInfo(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateFullUser(userDTO));
    }

    @GetMapping(value = "/user/get-all")
    public ResponseEntity<Collection<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(value = "/user/get-all-avatars")
    public ResponseEntity<Collection<UserDTO>> getAllWithAvatars() {
        return ResponseEntity.ok(userService.getAllWithAvatars());
    }

    @GetMapping(value = "/user/getAllBlocked")
    public ResponseEntity<Collection<UserDTO>> getAllBlocked() {
        return ResponseEntity.ok(userService.getAllBlocked());
    }

    @PostMapping(value = "/user/unlockAccounts")
    public ResponseEntity<Collection<UserDTO>> unlockAccounts(@RequestBody Collection<UserDTO> users) {
        return ResponseEntity.ok(userService.unlockUsers(users));
    }

    @PostMapping(value = "/user/removeAccounts")
    public ResponseEntity<Collection<UserDTO>> removeAccounts(@RequestBody Collection<UserDTO> users) {
        return ResponseEntity.ok(userService.removeAccounts(users));
    }

    @PostMapping(value = "/user/add", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO> update(@RequestBody RegisterRequest registerRequest) {
        userService.addUser(registerRequest);
        return ResponseEntity.ok().build();
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
}
