package com.edu.pm.backend.commons.mappers;

import com.edu.pm.backend.commons.dto.UserDTO;
import com.edu.pm.backend.model.User;

public class UserMapper {

    private UserMapper() {
    }

    public static User dtoToModel(UserDTO dto) {
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .roles(dto.getRoles())
                .team(TeamMapper.dtoToModel(dto.getTeam()))
                .projects(ProjectMapper.projectDTOListToModel(dto.getProjects()))
                .build();
    }

    public static UserDTO modelToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .team(TeamMapper.modelToDTO(user.getTeam()))
                .projects(ProjectMapper.projectModelToDtO(user.getProjects()))
                .build();
    }
}
