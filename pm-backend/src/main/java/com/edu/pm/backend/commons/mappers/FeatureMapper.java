package com.edu.pm.backend.commons.mappers;

import com.edu.pm.backend.commons.dto.FeatureDTO;
import com.edu.pm.backend.model.Feature;

public class FeatureMapper {

    private FeatureMapper() {
    }

    public static Feature dtoToModel(FeatureDTO dto) {
        return Feature.builder()
                .id(dto.getId())
                .featureName(dto.getFeatureName())
                .description(dto.getDescription())
                .project(ProjectMapper.dtoToModel(dto.getProject()))
                .build();
    }

    public static FeatureDTO modelToDTO(Feature feature) {
        return FeatureDTO.builder()
                .id(feature.getId())
                .featureName(feature.getFeatureName())
                .description(feature.getDescription())
                .project(ProjectMapper.modelToDTO(feature.getProject()))
                .build();
    }

}
