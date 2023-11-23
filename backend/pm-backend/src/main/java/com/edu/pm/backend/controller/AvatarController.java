package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.AvatarDTO;
import com.edu.pm.backend.model.User;
import com.edu.pm.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class AvatarController {

    private final UserRepository userRepository;
    private final String uploadDirectory = "uploads/avatars/";

    @PostMapping(value = "/user/avatar")
    public ResponseEntity<AvatarDTO> uploadAvatar(@RequestParam("image") MultipartFile image, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();

        try {
            String originalFileName = Objects.requireNonNull(image.getOriginalFilename());
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String fileName = "avatar_" + user.getId() + fileExtension;
            String filePath = uploadDirectory + fileName;

            File existingFile = new File(filePath);
            if (existingFile.exists()) {
                existingFile.delete();
            }
            byte[] imageBytes = image.getBytes();

            try (OutputStream stream = new FileOutputStream(filePath)) {
                stream.write(imageBytes);
            }
            return ResponseEntity.ok(new AvatarDTO(imageBytes));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/user/avatar")
    public ResponseEntity<AvatarDTO> getAvatar(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();

        String fileName = "avatar_" + user.getId();

        String originalFileName = null;
        String fileExtension = "";

        File[] matchingFiles = new File(uploadDirectory).listFiles(
                (dir, name) -> name.startsWith(fileName) && name.contains("."));
        if (matchingFiles != null && matchingFiles.length > 0) {
            originalFileName = matchingFiles[0].getName();
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        if (fileExtension.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        File avatarFile = new File(uploadDirectory + fileName + fileExtension);

        if (avatarFile.exists()) {
            try {
                byte[] avatarBytes = StreamUtils.copyToByteArray(new FileInputStream(avatarFile));

                return ResponseEntity.ok(new AvatarDTO(avatarBytes));
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
