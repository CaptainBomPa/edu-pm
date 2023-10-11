package com.edu.pm.backend.commons.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamDTO {

    private Integer id;
    private String teamName;
}
