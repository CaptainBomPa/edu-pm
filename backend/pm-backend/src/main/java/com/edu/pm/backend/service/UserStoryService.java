package com.edu.pm.backend.service;

import com.edu.pm.backend.commons.dto.FeatureDTO;
import com.edu.pm.backend.commons.dto.UserStoryDTO;
import com.edu.pm.backend.commons.mappers.FeatureMapper;
import com.edu.pm.backend.commons.mappers.UserStoryMapper;
import com.edu.pm.backend.model.Feature;
import com.edu.pm.backend.model.User;
import com.edu.pm.backend.model.UserStory;
import com.edu.pm.backend.repository.UserRepository;
import com.edu.pm.backend.repository.cache.FeatureCache;
import com.edu.pm.backend.repository.cache.UserStoryCache;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.edu.pm.backend.commons.mappers.UserStoryMapper.dtoToModel;
import static com.edu.pm.backend.commons.mappers.UserStoryMapper.modelToDTO;

@Service
@RequiredArgsConstructor
public class UserStoryService {

    private final UserStoryCache userStoryCache;
    private final FeatureCache featureCache;
    private final UserRepository userRepository;

    public UserStoryDTO add(UserStoryDTO dto) {
        UserStory userStory = dtoToModel(dto);
        userStory = userStoryCache.add(userStory);
        return modelToDTO(userStory);
    }

    public UserStoryDTO update(UserStoryDTO dto) {
        UserStory userStoryFromDB = userStoryCache.getById(dto.getId());
        if (userStoryFromDB == null) {
            throw new IllegalArgumentException("Entity not found");
        }
        userStoryFromDB.setUserStoryName(dto.getUserStoryName());
        userStoryFromDB.setDescription(dto.getDescription());
            userStoryFromDB.setFeature(FeatureMapper.dtoToModel(dto.getFeature()));
            userStoryFromDB.setAssignedUser(userRepository.findById(dto.getAssignedUser().getId()).orElseThrow());
            userStoryFromDB.setStoryPoints(dto.getStoryPoints());
            userStoryFromDB.setState(dto.getState());
            if (!dto.isBlocked()) {
                userStoryFromDB.setBlocked(false);
                userStoryFromDB.setBlockReason(null);
            } else {
            userStoryFromDB.setBlocked(true);
            userStoryFromDB.setBlockReason(dto.getBlockReason());
        }

        return modelToDTO(userStoryCache.add(userStoryFromDB));
    }

    public List<UserStoryDTO> removeMultiple(List<Integer> ids) {
        List<UserStoryDTO> deletedEntities = new ArrayList<>();
        for (Integer id : ids) {
            deletedEntities.add(remove(id));
        }
        return deletedEntities;
    }

    public UserStoryDTO remove(Integer id) {
        UserStory userStory = findById(id);
        if (userStory == null) {
            throw new IllegalArgumentException("Entity not found");
        }
        userStoryCache.remove(userStory);
        return modelToDTO(userStory);
    }

    public Collection<UserStoryDTO> getAllByFeature(FeatureDTO featureDTO) {
        if (featureDTO != null) {
            if (featureDTO.getId() != null) {
                Feature featureToFilter = featureCache.getAll().stream()
                        .filter(item -> item.getId().equals(featureDTO.getId())).findFirst().orElse(null);
                if (featureToFilter != null) {
                    return filterByFeature(featureToFilter);
                }

            } else if (featureDTO.getFeatureName() != null) {
                Feature featureToFilter = featureCache.getAll().stream()
                        .filter(item -> item.getFeatureName().equals(featureDTO.getFeatureName())).findFirst().orElse(null);
                if (featureToFilter != null) {
                    return filterByFeature(featureToFilter);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<UserStoryDTO> filterByFeature(Feature feature) {
        return userStoryCache.getAll().stream()
                .filter(item -> item.getFeature().getId().equals(feature.getId()))
                .map(UserStoryMapper::modelToDTO)
                .collect(Collectors.toList());
    }

    @Nullable
    public UserStory findById(Integer id) {
        return userStoryCache.getById(id);
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
        return userStoryCache.getAll().stream().map(UserStoryMapper::modelToDTO).toList();
    }

}
