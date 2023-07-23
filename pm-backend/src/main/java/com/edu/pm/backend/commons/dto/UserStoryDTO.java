package com.edu.pm.backend.commons.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserStoryDTO {

    private Integer id;
    private String userStoryName;
    private String description;
    private FeatureDTO feature;
}
