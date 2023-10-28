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
                .project(ProjectMapper.dtoToModel(dto.getProject()))
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
    }

    public static UserDTO modelToDTO(User user) {
        UserDTO.UserDTOBuilder builder = UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .firstName(user.getFirstName())
                .lastName(user.getLastName());

        if (user.getTeam() != null) {
            builder.team(TeamMapper.modelToDTO(user.getTeam()));
        }
        if (user.getProject() != null) {
            builder.project(ProjectMapper.modelToDTO(user.getProject()));
        }
        return builder.build();
    }
}
