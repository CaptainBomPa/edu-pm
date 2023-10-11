package com.edu.pm.backend.commons.mappers;

import com.edu.pm.backend.commons.dto.ProjectDTO;
import com.edu.pm.backend.model.Project;

import java.util.List;

public class ProjectMapper {

    private ProjectMapper() {
    }

    public static ProjectDTO modelToDTO(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .projectName(project.getProjectName())
                .build();
    }

    public static Project dtoToModel(ProjectDTO dto) {
        return Project.builder()
                .id(dto.getId())
                .projectName(dto.getProjectName())
                .build();
    }

    public static List<Project> projectDTOListToModel(List<ProjectDTO> projectDTOList) {
        return projectDTOList.stream().map(ProjectMapper::dtoToModel).toList();
    }

    public static List<ProjectDTO> projectModelToDtO(List<Project> projectList) {
        return projectList.stream().map(ProjectMapper::modelToDTO).toList();
    }
}
