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
        UserDTO.UserDTOBuilder builder = UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles());

        if (user.getTeam() != null) {
            builder.team(TeamMapper.modelToDTO(user.getTeam()));
        }
        if (user.getProjects() != null) {
            builder.projects(ProjectMapper.projectModelToDtO(user.getProjects()));
        }
        return builder.build();
    }
}
