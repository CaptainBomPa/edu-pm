package com.edu.pm.backend.service;

import com.edu.pm.backend.commons.dto.TaskDTO;
import com.edu.pm.backend.commons.mappers.TaskMapper;
import com.edu.pm.backend.commons.mappers.UserStoryMapper;
import com.edu.pm.backend.model.Task;
import com.edu.pm.backend.repository.TaskRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.edu.pm.backend.commons.mappers.TaskMapper.dtoToModel;
import static com.edu.pm.backend.commons.mappers.TaskMapper.modelToDTO;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public TaskDTO add(TaskDTO dto) {
        Task task = dtoToModel(dto);
        task = repository.save(task);
        return modelToDTO(task);
    }

    public TaskDTO update(TaskDTO dto) {
        Task taskFromDB = repository.findById(dto.getId()).orElseThrow();
        taskFromDB.setTaskName(dto.getTaskName());
        taskFromDB.setDescription(dto.getDescription());
        taskFromDB.setUserStory(UserStoryMapper.dtoToModel(dto.getUserStory()));
        return modelToDTO(repository.save(taskFromDB));
    }

    public TaskDTO remove(Integer id) {
        Task task = findById(id);
        if (task == null) {
            throw new IllegalArgumentException("Entity not found");
        }
        repository.delete(task);
        return modelToDTO(task);
    }

    @Nullable
    public Task findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Collection<TaskDTO> findAll() {
        return repository.findAll().stream().map(TaskMapper::modelToDTO).toList();
    }
}
