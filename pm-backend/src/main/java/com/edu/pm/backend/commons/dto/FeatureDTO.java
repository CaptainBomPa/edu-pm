package com.edu.pm.backend.commons.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeatureDTO {

    @Schema(example = "15", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer id;
    @Schema(example = "Feature Name", requiredMode = Schema.RequiredMode.REQUIRED)
    private String featureName;
    @Schema(example = "Lorem ipsum...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private ProjectDTO project;
}
