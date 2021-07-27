package com.betterSDA.service;

import com.betterSDA.model.dto.Team;
import com.betterSDA.model.entity.TeamEntity;
import com.betterSDA.repo.TeamRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepo teamRepo;




    public void addTeam(Team team) {
        teamRepo.save(TeamEntity.builder()
                .studentEntitySet(team.getStudentSet().stream().map(DataConverter::toEntity).collect(Collectors.toSet()))
                .teacherEntitySet(team.getTeacherSet().stream().map(DataConverter::toEntity).collect(Collectors.toSet()))
                .build());
    }

    public void updateTeam(Team team) {
        teamRepo.save(TeamEntity.builder()
                .name(team.getName())
                .studentEntitySet(team.getStudentSet().stream().map(DataConverter::toEntity).collect(Collectors.toSet()))
                .teacherEntitySet(team.getTeacherSet().stream().map(DataConverter::toEntity).collect(Collectors.toSet()))
                .build());
    }

    public void deleteTeamByIdName (String name) {
        teamRepo.deleteById(name);
    }

    public List<Team> getAllTeams() {
        return teamRepo.findAll().stream().map(DataConverter::toDto)
                .collect(Collectors.toList());
    }

    public Team getTeamById(String id) {

        Optional<TeamEntity> teamEntity = teamRepo.findById(id);

        if (teamEntity.isPresent()) return DataConverter.toDto(teamEntity.get());

        throw new NoSuchElementException("No team found in database");

    }


}