package com.edu.pm.backend.service;

import com.edu.pm.backend.commons.dto.ProjectDTO;
import com.edu.pm.backend.commons.mappers.ProjectMapper;
import com.edu.pm.backend.model.Project;
import com.edu.pm.backend.repository.ProjectRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.edu.pm.backend.commons.mappers.ProjectMapper.dtoToModel;
import static com.edu.pm.backend.commons.mappers.ProjectMapper.modelToDTO;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectDTO add(ProjectDTO dto) {
        Project project = dtoToModel(dto);
        project = projectRepository.save(project);
        return modelToDTO(project);
    }

    public ProjectDTO update(ProjectDTO dto) {
        Project projectFromDB = findById(dto.getId());
        if (projectFromDB == null) {
            throw new IllegalArgumentException("Entity not found");
        }
        projectFromDB.setProjectName(dto.getProjectName());
        return modelToDTO(projectRepository.save(projectFromDB));
    }

    public ProjectDTO remove(Integer id) {
        Project project = findById(id);
        if (project == null) {
            throw new IllegalArgumentException("Entity not found");
        }
        projectRepository.delete(project);
        return modelToDTO(project);
    }

    @Nullable
    public Project findById(Integer id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Nullable
    public ProjectDTO findByIdDTO(Integer id) {
        Project project = findById(id);
        if (project != null) {
            return modelToDTO(project);
        }
        return null;
    }

    public Collection<ProjectDTO> findAll() {
        return projectRepository.findAll().stream().map(ProjectMapper::modelToDTO).toList();
    }
}
