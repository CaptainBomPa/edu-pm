package com.edu.pm.backend.service.entity;

import com.edu.pm.backend.commons.dto.FeatureDTO;
import com.edu.pm.backend.commons.dto.UserStoryDTO;
import com.edu.pm.backend.commons.mappers.FeatureMapper;
import com.edu.pm.backend.commons.mappers.ProjectMapper;
import com.edu.pm.backend.commons.mappers.UserStoryMapper;
import com.edu.pm.backend.model.Feature;
import com.edu.pm.backend.repository.FeatureRepository;
import com.edu.pm.backend.repository.UserStoryRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static com.edu.pm.backend.commons.mappers.FeatureMapper.dtoToModel;
import static com.edu.pm.backend.commons.mappers.FeatureMapper.modelToDTO;

@Service
@RequiredArgsConstructor
public class FeatureService {

    private final FeatureRepository featureRepository;
    private final UserStoryRepository userStoryRepository;

    public FeatureDTO add(FeatureDTO dto) {
        Feature feature = dtoToModel(dto);
        feature = featureRepository.save(feature);
        return modelToDTO(feature);
    }

    public FeatureDTO update(FeatureDTO dto) {
        Feature featureFromDB = findById(dto.getId());
        if (featureFromDB == null) {
            throw new IllegalArgumentException("Entity not found");
        }
        featureFromDB.setFeatureName(dto.getFeatureName());
        featureFromDB.setDescription(dto.getDescription());
        featureFromDB.setProject(ProjectMapper.dtoToModel(dto.getProject()));
        return modelToDTO(featureRepository.save(featureFromDB));
    }

    public FeatureDTO remove(Integer id) {
        Feature feature = findById(id);
        if (feature == null) {
            throw new IllegalArgumentException("Entity not found");
        }
        featureRepository.delete(feature);
        return modelToDTO(feature);
    }

    @Nullable
    public Feature findById(Integer id) {
        return featureRepository.findById(id).orElse(null);
    }

    @Nullable
    public FeatureDTO findByIdDTO(Integer id) {
        Feature feature = findById(id);
        if (feature != null) {
            return modelToDTO(feature);
        }
        return null;
    }

    public Collection<FeatureDTO> findAll() {
        return featureRepository.findAll().stream().map(FeatureMapper::modelToDTO).toList();
    }

    public List<UserStoryDTO> getUserStories(Integer featureId) {
        return userStoryRepository.findAll().stream()
                .filter(userStory -> userStory.getFeature().getId().equals(featureId))
                .map(UserStoryMapper::modelToDTO)
                .toList();
    }

}
