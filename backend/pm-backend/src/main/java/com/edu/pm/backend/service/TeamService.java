package com.edu.pm.backend.service;

import com.edu.pm.backend.commons.dto.TeamDTO;
import com.edu.pm.backend.commons.mappers.TeamMapper;
import com.edu.pm.backend.model.Team;
import com.edu.pm.backend.repository.TeamRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.edu.pm.backend.commons.mappers.TeamMapper.modelToDTO;


@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamDTO add(TeamDTO dto) {
        Team team = TeamMapper.dtoToModel(dto);
        team = teamRepository.save(team);
        return modelToDTO(team);
    }

    public TeamDTO update(TeamDTO dto) {
        Team teamFromDB = findById(dto.getId());
        if (teamFromDB == null) {
            throw new IllegalArgumentException("Entity not found");
        }
        teamFromDB.setTeamName(dto.getTeamName());
        return modelToDTO(teamRepository.save(teamFromDB));
    }

    public TeamDTO remove(Integer id) {
        Team team = findById(id);
        if (team == null) {
            throw new IllegalArgumentException("Entity not found");
        }
        teamRepository.delete(team);
        return modelToDTO(team);
    }

    @Nullable
    public Team findById(Integer id) {
        return teamRepository.findById(id).orElse(null);
    }

    @Nullable
    public TeamDTO findByIdDTO(Integer id) {
        Team team = findById(id);
        if (team != null) {
            return modelToDTO(team);
        }
        return null;
    }

    public Collection<TeamDTO> findAll() {
        return teamRepository.findAll().stream().map(TeamMapper::modelToDTO).toList();
    }
}
