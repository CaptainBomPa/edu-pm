package com.edu.pm.backend.service;

import com.edu.pm.backend.commons.dto.TeamDTO;
import com.edu.pm.backend.commons.mappers.TeamMapper;
import com.edu.pm.backend.model.Team;
import com.edu.pm.backend.repository.cache.TeamCache;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.edu.pm.backend.commons.mappers.TeamMapper.modelToDTO;


@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamCache cache;

    public TeamDTO add(TeamDTO dto) {
        Team team = TeamMapper.dtoToModel(dto);
        team = cache.add(team);
        return modelToDTO(team);
    }

    public TeamDTO update(TeamDTO dto) {
        Team teamFromDB = cache.getById(dto.getId());
        if (teamFromDB == null) {
            throw new IllegalArgumentException("Entity not found");
        }
        teamFromDB.setTeamName(dto.getTeamName());
        return modelToDTO(cache.add(teamFromDB));
    }

    public TeamDTO remove(Integer id) {
        Team team = findById(id);
        if (team == null) {
            throw new IllegalArgumentException("Entity not found");
        }
        cache.remove(team);
        return modelToDTO(team);
    }

    @Nullable
    public Team findById(Integer id) {
        return cache.getById(id);
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
        return cache.getAll().stream().map(TeamMapper::modelToDTO).toList();
    }
}
