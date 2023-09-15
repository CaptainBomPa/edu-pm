package com.edu.pm.backend.commons.dto;

import com.edu.pm.backend.model.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {

    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private List<Role> roles;
    private TeamDTO team;
    private List<ProjectDTO> projects;
    byte[] avatar;
}
