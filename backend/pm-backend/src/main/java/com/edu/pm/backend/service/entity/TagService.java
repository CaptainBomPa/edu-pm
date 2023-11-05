package com.edu.pm.backend.service.entity;

import com.edu.pm.backend.model.Tag;
import com.edu.pm.backend.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Tag add(Tag tag) {
        return tagRepository.save(tag);
    }

    public Collection<Tag> getAll() {
        return tagRepository.findAll();
    }
}
