package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.AvatarDTO;
import com.edu.pm.backend.service.AvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
@CrossOrigin(origins = "${pm.cross-origin}")
public class AvatarController {

    private final AvatarService avatarService;

    @CrossOrigin(origins = "${pm.cross-origin}")
    @PostMapping(value = "/user/upload-avatar")
    public ResponseEntity<AvatarDTO> uploadAvatar(@RequestParam("image") MultipartFile image, Authentication authentication) {
        try {
            AvatarDTO dto = avatarService.uploadAvatar(authentication.getName(), image);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @CrossOrigin(origins = "${pm.cross-origin}")
    @GetMapping(value = "/user/my-avatar")
    public ResponseEntity<AvatarDTO> getAvatar(Authentication authentication) {
        return ResponseEntity.ok(avatarService.getUserAvatar(authentication.getName()));
    }


}
