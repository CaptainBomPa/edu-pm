package com.edu.pm.backend.commons.mappers;

import com.edu.pm.backend.commons.dto.TeamDTO;
import com.edu.pm.backend.model.Team;

public class TeamMapper {

    private TeamMapper() {
    }

    public static Team dtoToModel(TeamDTO dto) {
        return Team.builder()
                .id(dto.getId())
                .teamName(dto.getTeamName())
                .build();
    }

    public static TeamDTO modelToDTO(Team team) {
        return TeamDTO.builder()
                .id(team.getId())
                .teamName(team.getTeamName())
                .build();
    }
}
