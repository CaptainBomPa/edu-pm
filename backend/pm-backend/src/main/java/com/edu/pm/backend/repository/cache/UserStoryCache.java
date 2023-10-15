package com.edu.pm.backend.repository.cache;

import com.edu.pm.backend.model.UserStory;
import com.edu.pm.backend.repository.UserStoryRepository;
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
public class UserStoryCache {

    private final UserStoryRepository repository;

    private LoadingCache<Integer, UserStory> cache;

    @PostConstruct
    public void initCache() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(Integer.MAX_VALUE)
                .expireAfterWrite(Long.MAX_VALUE, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public UserStory load(Integer key) {
                        return repository.findById(key).orElseThrow();
                    }
                });
    }

    public UserStory add(@NotNull UserStory userStory) {
        UserStory savedUserStory = repository.save(userStory);
        cache.put(savedUserStory.getId(), savedUserStory);
        return savedUserStory;
    }

    public UserStory remove(UserStory userStory) {
        repository.delete(userStory);
        cache.asMap().remove(userStory.getId());
        return userStory;
    }

    @Nullable
    public UserStory getById(Integer id) {
        UserStory userStory = cache.getIfPresent(id);
        if (userStory == null) {
            return repository.findById(id).orElse(null);
        }
        return userStory;
    }

    public Set<UserStory> getAllCached() {
        return new HashSet<>(cache.asMap().values());
    }

    public Set<UserStory> getAllDatabase() {
        return new HashSet<>(repository.findAll());
    }

    public Set<UserStory> getAll() {
//        Set<UserStory> userStories = getAllCached();
//        userStories.addAll(getAllDatabase());
//        return userStories;
        return getAllDatabase();
    }
}
