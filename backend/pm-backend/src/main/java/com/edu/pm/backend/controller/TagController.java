package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.TagDTO;
import com.edu.pm.backend.model.Tag;
import com.edu.pm.backend.service.entity.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class TagController {

    private final TagService tagService;

    @GetMapping(value = "/tags")
    public ResponseEntity<Collection<Tag>> getAll() {
        return ResponseEntity.ok(tagService.getAll());
    }

    @GetMapping(value = "/tags/stats")
    public ResponseEntity<Collection<TagService.TagWithStats>> getAllWithStats() {
        return ResponseEntity.ok(tagService.getAllWithStats());
    }

    @PostMapping(value = "/tags")
    public ResponseEntity<TagService.TagWithStats> addTag(@RequestBody TagDTO tag) {
        return ResponseEntity.ok(tagService.add(tag));
    }

    @DeleteMapping(value = "/tags/{id}")
    public ResponseEntity<Tag> deleteTag(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(tagService.removeById(id));
    }
}
