package com.edu.pm.backend.service;

import com.edu.pm.backend.commons.dto.UserStoryDTO;
import com.edu.pm.backend.commons.mappers.UserStoryMapper;
import com.edu.pm.backend.model.Iteration;
import com.edu.pm.backend.model.Team;
import com.edu.pm.backend.model.User;
import com.edu.pm.backend.model.UserStory;
import com.edu.pm.backend.repository.IterationRepository;
import com.edu.pm.backend.repository.cache.UserStoryCache;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class IterationService {

    private final IterationRepository repository;
    private final UserService userService;
    private final UserStoryCache userStoryCache;

    /**
     * Iteration is nullable. If not given, then it takes the current iteration
     * Username is nullable. If not given, then in takes all user stories for all teams.
     */
    public Collection<UserStoryDTO> getUserStories(@Nullable String forUsername, @Nullable Integer iterationId) {
        User forUser = userService.findByUsername(forUsername);
        Iteration iterationToPass;
        if (iterationId != null) {
            iterationToPass = repository.findById(iterationId).orElseThrow(() -> new IllegalArgumentException("Iteration was not found"));
        } else {
            iterationToPass = getCurrentIteration();
        }

        if (forUser != null) {
            User user = userService.findByUsername(forUsername);
            assert user != null;
            return getUserStoriesForIterationAndTeam(iterationToPass, user.getTeam())
                    .stream()
                    .map(UserStoryMapper::modelToDTO)
                    .toList();
        } else {
            return getUserStoriesForIteration(iterationToPass)
                    .stream()
                    .map(UserStoryMapper::modelToDTO)
                    .toList();
        }
    }

    private Iteration getCurrentIteration() {
        LocalDateTime dateTime = LocalDateTime.now();
        Collection<Iteration> iterations = repository.findAll();
        return iterations.stream()
                .filter(item -> item.getStartDate().isBefore(dateTime) && item.getEndDate().isAfter(dateTime))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No iteration was found"));
    }

    private Collection<UserStory> getUserStoriesForIteration(Iteration iteration) {
        return userStoryCache.getAllCached().stream()
                .filter(userStory -> userStory.getIteration() != null)
                .filter(userStory -> userStory.getIteration().getItNumber().equals(iteration.getItNumber()))
                .toList();
    }

    private Collection<UserStory> getUserStoriesForIterationAndTeam(Iteration iteration, Team team) {
        return getUserStoriesForIteration(iteration).stream()
                .filter(userStory -> userStory.getTeam() != null)
                .filter(userStory -> userStory.getTeam().getId().equals(team.getId()))
                .toList();
    }


}
