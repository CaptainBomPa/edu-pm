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
                .role(dto.getRole())
                .team(TeamMapper.dtoToModel(dto.getTeam()))
                .build();
    }

    public static UserDTO modelToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .team(TeamMapper.modelToDTO(user.getTeam()))
                .build();
    }
}
