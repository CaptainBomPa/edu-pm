package com.edu.pm.backend.repository;

import com.edu.pm.backend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
