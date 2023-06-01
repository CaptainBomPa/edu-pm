package com.edu.pm.backend.repository;

import com.edu.pm.backend.model.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStoryRepository extends JpaRepository<UserStory, Integer> {
}
