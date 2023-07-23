package com.edu.pm.backend.controller;

import com.edu.pm.backend.commons.dto.FeatureDTO;
import com.edu.pm.backend.commons.dto.IdentityDTO;
import com.edu.pm.backend.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FeatureController {

    private final FeatureService featureService;

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

}
