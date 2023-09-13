package com.edu.pm.backend.service;

import com.edu.pm.backend.commons.dto.FeatureDTO;
import com.edu.pm.backend.commons.dto.UserStoryDTO;
import com.edu.pm.backend.commons.mappers.FeatureMapper;
import com.edu.pm.backend.commons.mappers.UserStoryMapper;
import com.edu.pm.backend.model.Feature;
import com.edu.pm.backend.model.UserStory;
import com.edu.pm.backend.repository.FeatureRepository;
import com.edu.pm.backend.repository.UserStoryRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.edu.pm.backend.commons.mappers.UserStoryMapper.dtoToModel;
import static com.edu.pm.backend.commons.mappers.UserStoryMapper.modelToDTO;

@Service
@RequiredArgsConstructor
public class UserStoryService {

    private final UserStoryRepository repository;
    private final FeatureRepository featureRepository; //TODO make cache

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

    public Collection<UserStoryDTO> getAllByFeature(FeatureDTO featureDTO) {
        if (featureDTO != null) {
            if (featureDTO.getId() != null) {
                Feature featureToFilter = featureRepository.findAll().stream()
                        .filter(item -> item.getId().equals(featureDTO.getId())).findFirst().orElse(null);
                if (featureToFilter != null) {
                    return filterByFeature(featureToFilter);
                }

            } else if (featureDTO.getFeatureName() != null) {
                Feature featureToFilter = featureRepository.findAll().stream()
                        .filter(item -> item.getFeatureName().equals(featureDTO.getFeatureName())).findFirst().orElse(null);
                if (featureToFilter != null) {
                    return filterByFeature(featureToFilter);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<UserStoryDTO> filterByFeature(Feature feature) {
        return repository.findAll().stream()
                .filter(item -> item.getFeature().getId().equals(feature.getId()))
                .map(UserStoryMapper::modelToDTO)
                .collect(Collectors.toList());
    }

    @Nullable
    public UserStory findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Nullable
    public UserStoryDTO findByIdDTO(Integer id) {
        UserStory userStory = findById(id);
        if (userStory != null) {
            return modelToDTO(userStory);
        }
        return null;
    }

    public Collection<UserStoryDTO> findAll() {
        return repository.findAll().stream().map(UserStoryMapper::modelToDTO).toList();
    }

}
