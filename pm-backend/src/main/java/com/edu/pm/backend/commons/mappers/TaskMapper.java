package com.edu.pm.backend.commons.mappers;

import com.edu.pm.backend.commons.dto.TaskDTO;
import com.edu.pm.backend.model.Task;

public class TaskMapper {

    private TaskMapper() {
    }

    public static Task dtoToModel(TaskDTO dto) {
        return Task.builder()
                .id(dto.getId())
                .taskName(dto.getTaskName())
                .description(dto.getDescription())
                .userStory(UserStoryMapper.dtoToModel(dto.getUserStory()))
                .build();
    }

    public static TaskDTO modelToDTO(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .taskName(task.getTaskName())
                .description(task.getDescription())
                .userStory(UserStoryMapper.modelToDTO(task.getUserStory()))
                .build();
    }
}
