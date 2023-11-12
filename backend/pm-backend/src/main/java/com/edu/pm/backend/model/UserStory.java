package com.edu.pm.backend.model;

import com.edu.pm.backend.model.enums.UserStoryState;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;

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
    private Integer storyPoints;

    @Column(length = 250, columnDefinition = "varchar(250) default 'NEW'")
    @Enumerated(value = EnumType.STRING)
    private UserStoryState state = UserStoryState.NEW;

    @OneToMany(mappedBy = "userStory", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @ManyToOne
    private Feature feature;

    @ManyToOne
    @Nullable
    private Team team;

    @OneToOne
    @Nullable
    private User assignedUser;

    @ManyToOne
    @Nullable
    private Iteration iteration;

    @Column
    @Length(max = 2000)
    private String description;

    @Column(columnDefinition = "boolean default false")
    private boolean blocked = false;

    @Column
    private String blockReason;

    @ManyToMany
    private Set<Tag> tags;

}
