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
public class Iteration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itNumber;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;
}
