package com.edu.pm.backend.repository;

import com.edu.pm.backend.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Integer> {
}
