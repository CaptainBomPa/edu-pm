package com.edu.pm.backend.service;

import com.edu.pm.backend.commons.dto.UserDTO;
import com.edu.pm.backend.commons.dto.auth.ChangePasswordDTO;
import com.edu.pm.backend.commons.dto.auth.RegisterRequest;
import com.edu.pm.backend.commons.mappers.UserMapper;
import com.edu.pm.backend.model.Project;
import com.edu.pm.backend.model.Team;
import com.edu.pm.backend.model.User;
import com.edu.pm.backend.model.enums.Role;
import com.edu.pm.backend.repository.ProjectRepository;
import com.edu.pm.backend.repository.TeamRepository;
import com.edu.pm.backend.repository.UserRepository;
import com.edu.pm.backend.repository.cache.ProjectCache;
import com.edu.pm.backend.repository.cache.TeamCache;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final TeamCache teamCache;
    private final TeamRepository teamRepository;
    private final ProjectCache projectCache;
    private final ProjectRepository projectRepository;
    private final PasswordEncoder passwordEncoder;
    private final AvatarService avatarService;

    public UserDTO getCurrentUser(String username) {
        return UserMapper.modelToDTO(repository.findByUsername(username).orElseThrow());
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = repository.findById(userDTO.getId()).orElseThrow();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        return UserMapper.modelToDTO(repository.save(user));
    }

    public UserDTO updateFullUser(UserDTO userDTO) {
        User user = repository.findById(userDTO.getId()).orElseThrow();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRoles(userDTO.getRoles());
        if (userDTO.getProject() != null) {
            Project project = projectRepository.findById(userDTO.getProject().getId()).orElseThrow();
            user.setProject(project);
        } else {
            user.setProject(null);
        }

        if (userDTO.getTeam() != null) {
            Team team = teamRepository.findById(userDTO.getTeam().getId()).orElseThrow();
            user.setTeam(team);
        } else {
            user.setTeam(null);
        }

        return UserMapper.modelToDTO(repository.save(user));
    }

    public void addUser(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .accountActivated(false)
                .build();
        repository.save(user);
    }

    public Collection<UserDTO> removeAccounts(Collection<UserDTO> userDTOS) {
        Collection<UserDTO> removedUsers = new ArrayList<>();
        for (UserDTO userDTO : userDTOS) {
            removedUsers.add(removeUser(userDTO));
        }
        return removedUsers;
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

    public Collection<UserDTO> getAllWithAvatars() {
        Collection<UserDTO> usersWithAvatar = getAll();
        usersWithAvatar.forEach(userDTO -> {
            byte[] optionalImg = avatarService.getAvatarForUserId(userDTO.getId());
            if (optionalImg != null) {
                userDTO.setAvatar(optionalImg);
            }
        });
        return usersWithAvatar;
    }

    public Collection<UserDTO> getAllBlocked() {
        return repository.findAll().stream()
                .filter(user -> !user.isAccountActivated())
                .map(UserMapper::modelToDTO)
                .toList();
    }

    public Collection<UserDTO> unlockUsers(Collection<UserDTO> userDTOS) {
        Collection<User> users = new ArrayList<>();
        for (UserDTO userDTO : userDTOS) {
            users.add(repository.findById(userDTO.getId()).orElseThrow());
        }
        for (User user : users) {
            user.setAccountActivated(true);
        }
        return repository.saveAll(users).stream().map(UserMapper::modelToDTO).toList();
    }

    @Nullable
    private User findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Nullable
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    public void changePassword(ChangePasswordDTO changePasswordDTO, String username) {
        User user = findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found in database");
        }

        if (passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
            repository.save(user);
        } else {
            throw new IllegalArgumentException("Given old password is not matching");
        }
    }

    public UserDTO addRole(Integer userId, Role newRole) {
        User user = findUserByIdOrThrow(userId);
        user.addRole(newRole);
        return UserMapper.modelToDTO(repository.save(user));
    }

    public UserDTO removeRole(Integer userId, Role roleToRemove) {
        User user = findUserByIdOrThrow(userId);
        user.removeRole(roleToRemove);
        return UserMapper.modelToDTO(repository.save(user));
    }

    public UserDTO updateTeam(Integer userId, String newTeam) {
        User user = findUserByIdOrThrow(userId);
        Team team = teamCache.getAll().stream()
                .filter(item -> item.getTeamName().equals(newTeam)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Team not found in database"));
        user.setTeam(team);
        return UserMapper.modelToDTO(repository.save(user));
    }

    private User findUserByIdOrThrow(Integer userId) {
        User user = findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found in database");
        }
        return user;
    }

}
