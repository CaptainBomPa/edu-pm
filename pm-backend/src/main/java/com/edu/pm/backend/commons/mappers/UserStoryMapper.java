package com.edu.pm.backend.commons.mappers;

import com.edu.pm.backend.commons.dto.UserStoryDTO;
import com.edu.pm.backend.model.UserStory;

public class UserStoryMapper {

    private UserStoryMapper() {
    }

    public static UserStoryDTO modelToDTO(UserStory userStory) {
        return UserStoryDTO.builder()
                .id(userStory.getId())
                .userStoryName(userStory.getUserStoryName())
                .description(userStory.getDescription())
                .feature(FeatureMapper.modelToDTO(userStory.getFeature()))
                .build();
    }

    public static UserStory dtoToModel(UserStoryDTO dto) {
        return UserStory.builder()
                .id(dto.getId())
                .userStoryName(dto.getUserStoryName())
                .description(dto.getUserStoryName())
                .feature(FeatureMapper.dtoToModel(dto.getFeature()))
                .build();
    }
}
