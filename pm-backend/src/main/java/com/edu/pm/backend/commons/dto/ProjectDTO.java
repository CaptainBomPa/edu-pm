package com.edu.pm.backend.commons.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectDTO {

    private Integer id;
    private String projectName;
}
