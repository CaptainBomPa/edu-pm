package com.edu.pm.backend.commons.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskDTO {

    private Integer id;
    private String taskName;
    private String description;
    private UserStoryDTO userStory;
}
