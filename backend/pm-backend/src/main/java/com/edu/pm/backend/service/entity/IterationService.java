package com.edu.pm.backend.service.entity;

import com.edu.pm.backend.commons.dto.TaskDTO;
import com.edu.pm.backend.commons.dto.UserStoryDTO;
import com.edu.pm.backend.commons.mappers.TaskMapper;
import com.edu.pm.backend.commons.mappers.UserStoryMapper;
import com.edu.pm.backend.model.Iteration;
import com.edu.pm.backend.model.Team;
import com.edu.pm.backend.model.User;
import com.edu.pm.backend.model.UserStory;
import com.edu.pm.backend.repository.IterationRepository;
import com.edu.pm.backend.repository.TaskRepository;
import com.edu.pm.backend.repository.TeamRepository;
import com.edu.pm.backend.repository.UserStoryRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IterationService {

    private final IterationRepository repository;
    private final UserService userService;
    private final UserStoryRepository userStoryRepository;
    private final TaskRepository taskRepository;
    private final TeamRepository teamRepository;

    public Collection<Iteration> getAll() {
        return repository.findAll();
    }

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
            return formatListToDTOAndAddTasks(getUserStoriesForIterationAndTeam(iterationToPass, user.getTeam()));
        }
        return formatListToDTOAndAddTasks(getUserStoriesForIteration(iterationToPass));
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
        return userStoryRepository.findAll().stream()
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

    public Collection<UserStoryDTO> getCurrentIterationForTeamId(Integer teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow();
        return formatListToDTOAndAddTasks(getUserStoriesForIterationAndTeam(getCurrentIteration(), team));
    }

    public Collection<UserStoryDTO> getStoriesForIterationAndTeam(Integer iterationId, Integer teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow();
        Iteration iteration = repository.findById(iterationId).orElseThrow();
        return formatListToDTOAndAddTasks(getUserStoriesForIterationAndTeam(iteration, team));
    }

    public Collection<UserStoryDTO> getBacklogItemsForUser(String name) {
        User forUser = userService.findByUsername(name);
        Team team = teamRepository.findById(forUser.getTeam().getId()).orElse(null);
        if (forUser != null && team != null) {
            return getBacklogItemsForTeamId(team.getId());
        }
        return Collections.emptyList();
    }

    public Collection<UserStoryDTO> getBacklogItemsForTeamId(Integer teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow();
        Collection<UserStory> userStories = findAll().stream()
                .filter(userStory -> userStory.getTeam() != null)
                .filter(userStory -> userStory.getTeam().getId().equals(team.getId()))
                .filter(userStory -> userStory.getIteration() == null).toList();
        return formatListToDTOAndAddTasks(userStories);
    }

    public Collection<UserStoryDTO> getProjectBacklog() {
        Collection<UserStory> userStories = findAll().stream()
                .filter(userStory -> userStory.getTeam() == null)
                .filter(userStory -> userStory.getIteration() == null).toList();
        return formatListToDTOAndAddTasks(userStories);
    }

    private Collection<UserStory> findAll() {
        return userStoryRepository.findAll();
    }

    private Collection<UserStoryDTO> formatListToDTOAndAddTasks(Collection<UserStory> userStories) {
        Collection<TaskDTO> tasks = taskRepository.findAll().stream().map(TaskMapper::modelToDTO).collect(Collectors.toSet());
        return userStories.stream().map(UserStoryMapper::modelToDTO)
                .peek(story -> story.setTasks(tasks.stream()
                        .filter(task -> task.getUserStory().getId().equals(story.getId()))
                        .collect(Collectors.toSet())))
                .toList();
    }
}
