package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.IdentityDTO;
import com.edu.pm.backend.commons.dto.UserStoryDTO;
import com.edu.pm.backend.model.Iteration;
import com.edu.pm.backend.service.IterationService;
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
public class IterationController {

    private final IterationService iterationService;

    @GetMapping(value = "/iteration/currentForUser")
    public ResponseEntity<Collection<UserStoryDTO>> getUserStoriesForUser(Authentication authentication) {
        return ResponseEntity.ok(iterationService.getUserStories(authentication.getName(), null));
    }

    @GetMapping(value = "/iteration/currentForTeamId/{id}")
    public ResponseEntity<Collection<UserStoryDTO>> getUserStoriesForTeamId(@PathVariable("id") final String teamId) {
        return null;
    }

    @GetMapping(value = "/iteration/currentAll")
    public ResponseEntity<Collection<UserStoryDTO>> getAllUserStories() {
        return ResponseEntity.ok(iterationService.getUserStories(null, null));
    }

    @PostMapping(value = "/iteration/userStoriesForId", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Collection<UserStoryDTO>> getUserStoriesForIteration(@RequestBody IdentityDTO identityDTO) {
        return ResponseEntity.ok(iterationService.getUserStories(null, identityDTO.getId()));
    }

    @PostMapping(value = "/iteration/userStoriesForIdAndUser", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Collection<UserStoryDTO>> getUserStoriesForIterationAndUser(@RequestBody IdentityDTO identityDTO, Authentication authentication) {
        return ResponseEntity.ok(iterationService.getUserStories(authentication.getName(), identityDTO.getId()));
    }

    @GetMapping(value = "/iteration/get-all")
    public ResponseEntity<Collection<Iteration>> getAllIterations() {
        return ResponseEntity.ok(iterationService.getAll());
    }

}
