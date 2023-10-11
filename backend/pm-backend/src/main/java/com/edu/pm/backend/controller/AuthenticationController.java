package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.auth.AuthenticationRequest;
import com.edu.pm.backend.commons.dto.auth.AuthenticationResponse;
import com.edu.pm.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "${pm.cross-origin}")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/api/auth/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
