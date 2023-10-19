package com.edu.pm.backend.commons.dto;

import com.edu.pm.backend.model.Iteration;
import com.edu.pm.backend.model.enums.UserStoryState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStoryDTO {

    private Integer id;
    private String userStoryName;
    private String description;
    private FeatureDTO feature;
    private UserDTO assignedUser;
    private Iteration iteration;
    private Integer storyPoints;
    private Collection<TaskDTO> tasks;
    private UserStoryState state;
    private TeamDTO team;
    private boolean blocked;
    private String blockReason;
}
