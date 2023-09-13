package com.edu.pm.backend.repository.cache;

import com.edu.pm.backend.model.Feature;
import com.edu.pm.backend.repository.FeatureRepository;
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
public class FeatureCache {

    private final FeatureRepository repository;

    private LoadingCache<Integer, Feature> cache;

    @PostConstruct
    public void initCache() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(Integer.MAX_VALUE)
                .expireAfterWrite(Long.MAX_VALUE, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public Feature load(Integer key) {
                        return repository.findById(key).orElseThrow();
                    }
                });
    }

    public Feature add(@NotNull Feature feature) {
        Feature savedFeature = repository.save(feature);
        cache.put(savedFeature.getId(), savedFeature);
        return savedFeature;
    }

    public Feature remove(Feature feature) {
        repository.delete(feature);
        cache.asMap().remove(feature.getId());
        return feature;
    }

    @Nullable
    public Feature getById(Integer id) {
        Feature feature = cache.getIfPresent(id);
        if (feature == null) {
            return repository.findById(id).orElse(null);
        }
        return feature;
    }

    public Set<Feature> getAllCached() {
        return new HashSet<>(cache.asMap().values());
    }

    public Set<Feature> getAllDatabase() {
        return new HashSet<>(repository.findAll());
    }

    public Set<Feature> getAll() {
        Set<Feature> projects = getAllCached();
        projects.addAll(getAllDatabase());
        return projects;
    }
}
