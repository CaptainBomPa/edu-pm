package com.edu.pm.backend.controller;

import com.edu.pm.backend.model.Tag;
import com.edu.pm.backend.service.entity.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
@CrossOrigin(origins = "${pm.cross-origin}")
public class TagController {

    private final TagService tagService;

    @GetMapping(value = "/tags/all")
    public ResponseEntity<Collection<Tag>> getAll() {
        return ResponseEntity.ok(tagService.getAll());
    }
}
