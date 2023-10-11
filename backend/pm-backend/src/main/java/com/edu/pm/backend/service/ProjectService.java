package com.edu.pm.backend.service;

import com.edu.pm.backend.commons.dto.ProjectDTO;
import com.edu.pm.backend.commons.mappers.ProjectMapper;
import com.edu.pm.backend.model.Project;
import com.edu.pm.backend.repository.cache.ProjectCache;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.edu.pm.backend.commons.mappers.ProjectMapper.dtoToModel;
import static com.edu.pm.backend.commons.mappers.ProjectMapper.modelToDTO;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectCache cache;

    public ProjectDTO add(ProjectDTO dto) {
        Project project = dtoToModel(dto);
        project = cache.add(project);
        return modelToDTO(project);
    }

    public ProjectDTO update(ProjectDTO dto) {
        Project projectFromDB = cache.getById(dto.getId());
        if (projectFromDB == null) {
            throw new IllegalArgumentException("Entity not found");
        }
        projectFromDB.setProjectName(dto.getProjectName());
        return modelToDTO(cache.add(projectFromDB));
    }

    public ProjectDTO remove(Integer id) {
        Project project = findById(id);
        if (project == null) {
            throw new IllegalArgumentException("Entity not found");
        }
        cache.remove(project);
        return modelToDTO(project);
    }

    @Nullable
    public Project findById(Integer id) {
        return cache.getById(id);
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
        return cache.getAll().stream().map(ProjectMapper::modelToDTO).toList();
    }
}
