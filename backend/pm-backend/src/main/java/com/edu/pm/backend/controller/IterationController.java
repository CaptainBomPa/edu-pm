package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.UserStoryDTO;
import com.edu.pm.backend.model.Iteration;
import com.edu.pm.backend.service.entity.IterationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class IterationController {

    private final IterationService iterationService;

    @GetMapping(value = "/iteration/current")
    public ResponseEntity<Collection<UserStoryDTO>> getUserStoriesForUser(Authentication authentication) {
        return ResponseEntity.ok(iterationService.getUserStories(authentication.getName(), null));
    }

    @GetMapping(value = "/iteration/team/{teamId}/iteration/{iterationId}")
    public ResponseEntity<Collection<UserStoryDTO>> getStoriesForIterationAndTeam(@PathVariable("iterationId") final Integer iterationId,
                                                                                  @PathVariable("teamId") final Integer teamId) {
        return ResponseEntity.ok(iterationService.getStoriesForIterationAndTeam(iterationId, teamId));
    }

    @GetMapping(value = "/iteration/backlog/current")
    public ResponseEntity<Collection<UserStoryDTO>> getBacklogItemsForUser(Authentication authentication) {
        return ResponseEntity.ok(iterationService.getBacklogItemsForUser(authentication.getName()));
    }

    @GetMapping(value = "/iteration/backlog/teamId/{teamId}")
    public ResponseEntity<Collection<UserStoryDTO>> getBacklogItemsForTeam(@PathVariable("teamId") final Integer teamId) {
        return ResponseEntity.ok(iterationService.getBacklogItemsForTeamId(teamId));
    }

    @GetMapping(value = "/iteration/backlog/project")
    public ResponseEntity<Collection<UserStoryDTO>> getProjectBacklog() {
        return ResponseEntity.ok(iterationService.getProjectBacklog());
    }

    @GetMapping(value = "/iteration")
    public ResponseEntity<Collection<Iteration>> getAllIterations() {
        return ResponseEntity.ok(iterationService.getAll());
    }

}
