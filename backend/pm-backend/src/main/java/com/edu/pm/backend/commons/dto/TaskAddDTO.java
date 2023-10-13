package com.edu.pm.backend.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskAddDTO {

    private Integer id;
    private Integer userStoryId;
    private String taskName;
    private String description;
}
