package com.edu.pm.backend.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String userStoryName;

    @Column
    private String description;

    @ManyToOne
    private Feature feature;

    @ManyToOne
    @Nullable
    private User assignedUser;

    @ManyToOne
    @Nullable
    private Iteration iteration;
}
