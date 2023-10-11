package com.edu.pm.backend.commons.dto.auth;

import com.edu.pm.backend.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {

    private Integer id;
    private Role role;
    private String team;
    private Integer projectId;
}
