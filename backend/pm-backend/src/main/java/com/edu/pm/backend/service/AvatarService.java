package com.edu.pm.backend.service;


import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class AvatarService {

    @Nullable
    public byte[] getAvatarForUserId(Integer id) {
        String[] extensionsToTry = {".jpg", ".jpeg", ".png"};

        for (String extension : extensionsToTry) {
            String fileName = "avatar_" + id + extension;
            String uploadDirectory = "uploads/avatars/";
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






