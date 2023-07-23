package com.edu.pm.backend.commons.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class IdentityDTO {

    @Schema(example = "15", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;
}
