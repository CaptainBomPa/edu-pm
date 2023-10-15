package com.edu.pm.backend.service;


import com.edu.pm.backend.commons.dto.AvatarDTO;
import com.edu.pm.backend.model.User;
import com.edu.pm.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class AvatarService {

    private final String uploadDirectory = "uploads/avatars/";

    public byte[] getAvatarForUserId(Integer id) {
        String[] extensionsToTry = {".jpg", ".jpeg", ".png"};

        for (String extension : extensionsToTry) {
            String fileName = "avatar_" + id + extension;
            File avatarFile = new File(uploadDirectory + fileName);

            if (avatarFile.exists()) {
                try {
                    return Files.readAllBytes(avatarFile.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}






