package com.edu.pm.backend.repository.cache;

import com.edu.pm.backend.model.Team;
import com.edu.pm.backend.repository.TeamRepository;
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
public class TeamCache {

    private final TeamRepository repository;

    private LoadingCache<Integer, Team> cache;

    @PostConstruct
    public void initCache() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(Integer.MAX_VALUE)
                .expireAfterWrite(Long.MAX_VALUE, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public Team load(Integer key) {
                        return repository.findById(key).orElseThrow();
                    }
                });
    }

    public Team add(@NotNull Team team) {
        Team savedTeam = repository.save(team);
        cache.put(savedTeam.getId(), savedTeam);
        return savedTeam;
    }

    public Team remove(Team team) {
        repository.delete(team);
        cache.asMap().remove(team.getId());
        return team;
    }

    @Nullable
    public Team getById(Integer id) {
        Team team = cache.getIfPresent(id);
        if (team == null) {
            return repository.findById(id).orElse(null);
        }
        return team;
    }

    public Set<Team> getAllCached() {
        return new HashSet<>(cache.asMap().values());
    }

    public Set<Team> getAllDatabase() {
        return new HashSet<>(repository.findAll());
    }

    public Set<Team> getAll() {
        Set<Team> projects = getAllCached();
        projects.addAll(getAllDatabase());
        return projects;
    }
}
