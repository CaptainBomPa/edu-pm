package com.edu.pm.backend.repository.cache;

import com.edu.pm.backend.model.Project;
import com.edu.pm.backend.repository.ProjectRepository;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import jakarta.annotation.Nullable;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ProjectCache {

    private final ProjectRepository repository;

    private LoadingCache<Integer, Project> cache;

    @PostConstruct
    public void initCache() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(Integer.MAX_VALUE)
                .expireAfterWrite(Long.MAX_VALUE, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public Project load(Integer key) {
                        return repository.findById(key).orElseThrow();
                    }
                });
    }

    public Project add(@NotNull Project project) {
        Project savedProject = repository.save(project);
        cache.put(savedProject.getId(), savedProject);
        return savedProject;
    }

    public Project remove(Project project) {
        repository.delete(project);
        cache.asMap().remove(project.getId());
        return project;
    }

    @Nullable
    public Project getById(Integer id) {
        Project project = cache.getIfPresent(id);
        if (project == null) {
            return repository.findById(id).orElse(null);
        }
        return project;
    }

    public Set<Project> getAllCached() {
        return new HashSet<>(cache.asMap().values());
    }

    public Set<Project> getAllDatabase() {
        return new HashSet<>(repository.findAll());
    }

    public Set<Project> getAll() {
        Set<Project> projects = getAllCached();
        projects.addAll(getAllDatabase());
        return projects;
    }

}
