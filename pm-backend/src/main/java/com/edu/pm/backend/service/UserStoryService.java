package com.edu.pm.backend.service;

import com.edu.pm.backend.commons.dto.UserStoryDTO;
import com.edu.pm.backend.commons.mappers.FeatureMapper;
import com.edu.pm.backend.commons.mappers.UserStoryMapper;
import com.edu.pm.backend.model.UserStory;
import com.edu.pm.backend.repository.UserStoryRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.edu.pm.backend.commons.mappers.UserStoryMapper.dtoToModel;
import static com.edu.pm.backend.commons.mappers.UserStoryMapper.modelToDTO;

@Service
@RequiredArgsConstructor
public class UserStoryService {

    private final UserStoryRepository repository;

    public UserStoryDTO add(UserStoryDTO dto) {
        UserStory userStory = dtoToModel(dto);
        userStory = repository.save(userStory);
        return modelToDTO(userStory);
    }

    public UserStoryDTO update(UserStoryDTO dto) {
        UserStory userStoryFromDB = repository.findById(dto.getId()).orElseThrow();
        userStoryFromDB.setUserStoryName(dto.getUserStoryName());
        userStoryFromDB.setDescription(dto.getDescription());
        userStoryFromDB.setFeature(FeatureMapper.dtoToModel(dto.getFeature()));
        return modelToDTO(repository.save(userStoryFromDB));
    }

    public UserStoryDTO remove(Integer id) {
        UserStory userStory = findById(id);
        if (userStory == null) {
            throw new IllegalArgumentException("Entity not found");
        }
        repository.delete(userStory);
        return modelToDTO(userStory);
    }

    @Nullable
    public UserStory findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Collection<UserStoryDTO> findAll() {
        return repository.findAll().stream().map(UserStoryMapper::modelToDTO).toList();
    }

}
