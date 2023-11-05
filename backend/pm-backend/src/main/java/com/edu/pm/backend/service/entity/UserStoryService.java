package com.edu.pm.backend.service.entity;

import com.edu.pm.backend.commons.dto.FeatureDTO;
import com.edu.pm.backend.commons.dto.UserStoryDTO;
import com.edu.pm.backend.commons.mappers.FeatureMapper;
import com.edu.pm.backend.commons.mappers.UserStoryMapper;
import com.edu.pm.backend.model.Feature;
import com.edu.pm.backend.model.UserStory;
import com.edu.pm.backend.repository.*;
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

    private final UserStoryRepository userStoryRepository;
    private final FeatureRepository featureRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final IterationRepository iterationRepository;

    public UserStoryDTO add(UserStoryDTO dto) {
        UserStory userStory = dtoToModel(dto);
        userStory = userStoryRepository.save(userStory);
        return modelToDTO(userStory);
    }

    public UserStoryDTO update(UserStoryDTO dto) {
        UserStory userStoryFromDB = findById(dto.getId());
        if (userStoryFromDB == null) {
            throw new IllegalArgumentException("Entity not found");
        }
        userStoryFromDB.setUserStoryName(dto.getUserStoryName());
        userStoryFromDB.setDescription(dto.getDescription());
        userStoryFromDB.setFeature(FeatureMapper.dtoToModel(dto.getFeature()));
        userStoryFromDB.setStoryPoints(dto.getStoryPoints());
        userStoryFromDB.setState(dto.getState());

        if (dto.getAssignedUser() != null) {
            userStoryFromDB.setAssignedUser(userRepository.findById(dto.getAssignedUser().getId()).orElseThrow());
        } else {
            userStoryFromDB.setAssignedUser(null);
        }

        if (!dto.isBlocked()) {
            userStoryFromDB.setBlocked(false);
            userStoryFromDB.setBlockReason(null);
        } else {
            userStoryFromDB.setBlocked(true);
            userStoryFromDB.setBlockReason(dto.getBlockReason());
        }

        if (dto.getTeam() != null) {
            userStoryFromDB.setTeam(teamRepository.findById(dto.getTeam().getId()).orElseThrow());
        } else {
            userStoryFromDB.setTeam(null);
        }

        if (dto.getIteration() != null) {
            userStoryFromDB.setIteration(iterationRepository.findById(dto.getIteration().getItNumber()).orElseThrow());
        } else {
            userStoryFromDB.setIteration(null);
        }

        return modelToDTO(userStoryRepository.save(userStoryFromDB));
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
        userStoryRepository.delete(userStory);
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
        return userStoryRepository.findAll().stream()
                .filter(item -> item.getFeature().getId().equals(feature.getId()))
                .map(UserStoryMapper::modelToDTO)
                .collect(Collectors.toList());
    }

    @Nullable
    public UserStory findById(Integer id) {
        return userStoryRepository.findById(id).orElse(null);
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
        return userStoryRepository.findAll().stream().map(UserStoryMapper::modelToDTO).toList();
    }

}
