package com.edu.pm.backend.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStoryDTO {

    private Integer id;
    private String userStoryName;
    private String description;
    private FeatureDTO feature;
}
