package com.edu.pm.backend.service;


import com.edu.pm.backend.commons.dto.AvatarDTO;
import com.edu.pm.backend.model.User;
import com.edu.pm.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AvatarService {

    private final UserRepository userRepository;

    public AvatarDTO uploadAvatar(String username, MultipartFile multipartFile) throws IOException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found in database"));

        byte[] imageBytes = multipartFile.getBytes();
        user.setAvatar(imageBytes);
        User savedUser = userRepository.save(user);
        AvatarDTO dto = new AvatarDTO();
        dto.setImage(savedUser.getAvatar());
        return dto;
    }

    public AvatarDTO getUserAvatar(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        AvatarDTO avatarDTO = new AvatarDTO();
        avatarDTO.setImage(user.getAvatar());
        return avatarDTO;
    }
}
