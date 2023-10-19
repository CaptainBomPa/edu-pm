package com.edu.pm.backend.commons.mappers;

import com.edu.pm.backend.commons.dto.UserStoryDTO;
import com.edu.pm.backend.model.UserStory;

public class UserStoryMapper {

    private UserStoryMapper() {
    }

    public static UserStoryDTO modelToDTO(UserStory userStory) {
        UserStoryDTO.UserStoryDTOBuilder builder = UserStoryDTO.builder()
                .id(userStory.getId())
                .userStoryName(userStory.getUserStoryName())
                .description(userStory.getDescription())
                .iteration(userStory.getIteration())
                .storyPoints(userStory.getStoryPoints())
                .state(userStory.getState())
                .blocked(userStory.isBlocked())
                .blockReason(userStory.getBlockReason());
        if (userStory.getFeature() != null) {
            builder.feature(FeatureMapper.modelToDTO(userStory.getFeature()));
        }
        if (userStory.getAssignedUser() != null) {
            builder.assignedUser(UserMapper.modelToDTO(userStory.getAssignedUser()));
        }
        if (userStory.getTeam() != null) {
            builder.team(TeamMapper.modelToDTO(userStory.getTeam()));
        }
        return builder.build();
    }

    public static UserStory dtoToModel(UserStoryDTO dto) {
        UserStory.UserStoryBuilder builder = UserStory.builder()
                .id(dto.getId())
                .userStoryName(dto.getUserStoryName())
                .description(dto.getUserStoryName())
                .iteration(dto.getIteration())
                .storyPoints(dto.getStoryPoints())
                .state(dto.getState())
                .blocked(dto.isBlocked())
                .blockReason(dto.getBlockReason());
        if (dto.getFeature() != null) {
            builder.feature(FeatureMapper.dtoToModel(dto.getFeature()));
        }
        if (dto.getAssignedUser() != null) {
            builder.assignedUser(UserMapper.dtoToModel(dto.getAssignedUser()));
        }
        if (dto.getTeam() != null) {
            builder.team(TeamMapper.dtoToModel(dto.getTeam()));
        }
        return builder.build();
    }
}
