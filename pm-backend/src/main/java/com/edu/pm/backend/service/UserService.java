package com.edu.pm.backend.service;

import com.edu.pm.backend.commons.dto.ProjectDTO;
import com.edu.pm.backend.commons.dto.UserDTO;
import com.edu.pm.backend.commons.mappers.UserMapper;
import com.edu.pm.backend.model.User;
import com.edu.pm.backend.model.enums.Role;
import com.edu.pm.backend.repository.UserRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO addUser(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .roles(userDTO.getRoles())
                .build();
        User savedUser = repository.save(user);
        return UserMapper.modelToDTO(savedUser);
    }

    public UserDTO removeUser(UserDTO userDTO) {
        User user = findById(userDTO.getId());
        if (user == null) {
            throw new IllegalArgumentException("Entity not found");
        }
        repository.delete(user);
        return UserMapper.modelToDTO(user);
    }

    public Collection<UserDTO> getAll() {
        return repository.findAll().stream().map(UserMapper::modelToDTO).toList();
    }

    @Nullable
    public User findById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    public void changePassword(Integer userId, String oldPassword, String newPassword) {

    }

    public void addRole(Integer userId, Role newRole) {

    }

    public void removeRole(Integer userId, Role roleToRemove) {

    }

    public void updateTeam(Integer userId, String newTeam) {

    }

    public void addProject(Integer userId, ProjectDTO projectDTO) {

    }

    public void removeProject(Integer userId, ProjectDTO projectDTO) {

    }


}
