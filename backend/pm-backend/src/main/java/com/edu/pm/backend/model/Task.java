package com.edu.pm.backend.model;


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
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String taskName;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "USER_STORY_ID")
    private UserStory userStory;
}
