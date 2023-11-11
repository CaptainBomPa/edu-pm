package com.edu.pm.backend.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeatureFullDTO {

    private Integer id;
    private String featureName;
    private String description;
    private ProjectDTO project;
    private Integer allStoryPoints;
    private Set<UserStoryDTO> userStories;
}
