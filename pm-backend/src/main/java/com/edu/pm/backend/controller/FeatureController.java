package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.FeatureDTO;
import com.edu.pm.backend.commons.dto.IdentityDTO;
import com.edu.pm.backend.commons.dto.UserStoryDTO;
import com.edu.pm.backend.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class FeatureController {

    private final FeatureService featureService;

    @GetMapping(value = "/feature/get-all")
    public ResponseEntity<Collection<FeatureDTO>> getAll() {
        return ResponseEntity.ok(featureService.findAll());
    }

    @PostMapping(value = "/feature/update", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FeatureDTO> update(@RequestBody FeatureDTO dto) {
        if (dto.getId() != null) {
            return ResponseEntity.ok(featureService.update(dto));
        }
        return ResponseEntity.ok(featureService.add(dto));
    }

    @PostMapping(value = "/feature/delete", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FeatureDTO> delete(@RequestBody IdentityDTO dto) {
        return ResponseEntity.ok(featureService.remove(dto.getId()));
    }

    @PostMapping(value = "/feature/getById", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FeatureDTO> getById(@RequestBody IdentityDTO dto) {
        return ResponseEntity.ok(featureService.findByIdDTO((dto.getId())));
    }

    @PostMapping(value = "/feature/getUSById", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UserStoryDTO>> getUserStories(@RequestBody IdentityDTO featureId) {
        return ResponseEntity.ok(featureService.getUserStories(featureId.getId()));
    }


}
