package com.edu.pm.backend.commons.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private Integer id;
    private String username;
    private String password;
    private String role;
    private TeamDTO team;
}
