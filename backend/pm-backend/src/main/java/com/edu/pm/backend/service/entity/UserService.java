package com.edu.pm.backend.service.entity;

import com.edu.pm.backend.commons.dto.MessageByUsersId;
import com.edu.pm.backend.commons.dto.UserDTO;
import com.edu.pm.backend.commons.dto.auth.ChangePasswordDTO;
import com.edu.pm.backend.commons.dto.auth.RegisterRequest;
import com.edu.pm.backend.commons.mappers.UserMapper;
import com.edu.pm.backend.model.Message;
import com.edu.pm.backend.model.Project;
import com.edu.pm.backend.model.Team;
import com.edu.pm.backend.model.User;
import com.edu.pm.backend.model.enums.Role;
import com.edu.pm.backend.repository.MessageRepository;
import com.edu.pm.backend.repository.ProjectRepository;
import com.edu.pm.backend.repository.TeamRepository;
import com.edu.pm.backend.repository.UserRepository;
import com.edu.pm.backend.service.AvatarService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final ProjectRepository projectRepository;
    private final PasswordEncoder passwordEncoder;
    private final AvatarService avatarService;
    private final MessageRepository messageRepository;

    public UserDTO getCurrentUser(String username) {
        return UserMapper.modelToDTO(userRepository.findByUsername(username).orElseThrow());
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = findById(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        return UserMapper.modelToDTO(userRepository.save(user));
    }

    public UserDTO updateFullUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElseThrow();
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

        return UserMapper.modelToDTO(userRepository.save(user));
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
        userRepository.save(user);
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
        userRepository.delete(user);
        return UserMapper.modelToDTO(user);
    }

    public Collection<UserDTO> getAll() {
        return userRepository.findAll().stream().map(UserMapper::modelToDTO).toList();
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
        return userRepository.findAll().stream()
                .filter(user -> !user.isAccountActivated())
                .map(UserMapper::modelToDTO)
                .toList();
    }

    public Collection<UserDTO> unlockUsers(Collection<UserDTO> userDTOS) {
        Collection<User> users = new ArrayList<>();
        for (UserDTO userDTO : userDTOS) {
            users.add(userRepository.findById(userDTO.getId()).orElseThrow());
        }
        for (User user : users) {
            user.setAccountActivated(true);
        }
        return userRepository.saveAll(users).stream().map(UserMapper::modelToDTO).toList();
    }

    @Nullable
    private User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Nullable
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public void changePassword(ChangePasswordDTO changePasswordDTO, String username) {
        User user = findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found in database");
        }

        if (passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Given old password is not matching");
        }
    }

    public UserDTO addRole(Integer userId, Role newRole) {
        User user = findUserByIdOrThrow(userId);
        user.addRole(newRole);
        return UserMapper.modelToDTO(userRepository.save(user));
    }

    public UserDTO removeRole(Integer userId, Role roleToRemove) {
        User user = findUserByIdOrThrow(userId);
        user.removeRole(roleToRemove);
        return UserMapper.modelToDTO(userRepository.save(user));
    }

    public UserDTO updateTeam(Integer userId, String newTeam) {
        User user = findUserByIdOrThrow(userId);
        Team team = teamRepository.findAll().stream()
                .filter(item -> item.getTeamName().equals(newTeam)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Team not found in database"));
        user.setTeam(team);
        return UserMapper.modelToDTO(userRepository.save(user));
    }

    private User findUserByIdOrThrow(Integer userId) {
        User user = findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found in database");
        }
        return user;
    }

    public List<Message> getMessagesByUsers(MessageByUsersId usersId) {
        final User user1 = userRepository.findById(usersId.getUser1Id()).orElseThrow();
        final User user2 = userRepository.findById(usersId.getUser2Id()).orElseThrow();
        return messageRepository.findAll().stream()
                .filter(message -> (message.getSender().getId().equals(user1.getId()) && message.getReceiver().getId().equals(user2.getId())) ||
                        (message.getSender().getId().equals(user2.getId()) && message.getReceiver().getId().equals(user1.getId())))
                .toList();
    }
}
