package com.edu.pm.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
/**
 * Iteration in UserStory can be null, which from business perspective means that this item is in Backlog.
 */
public class Iteration {

    //TODO create automatic mechanism that will check if new iteration are needed to be added on server startup or periodically
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itNumber;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;
}
