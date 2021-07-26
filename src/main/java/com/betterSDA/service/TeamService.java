package com.betterSDA.service;

import com.betterSDA.model.dto.Address;
import com.betterSDA.model.dto.Team;
import com.betterSDA.model.entity.AddressEntity;
import com.betterSDA.model.entity.TeamEntity;
import com.betterSDA.repo.TeamRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepo teamRepo;



//
//    public void addTeam(Team team) {
//        teamRepo.save(TeamEntity.builder()
//                .studentEntitySet(team.getStudentSet())
//                .teacherEntitySet(team.getTeacherEntitySet())
//                .build());
//    }
//
//    public void updateTeam(Team team) {
//        teamRepo.save(TeamEntity.builder()
//                .name(team.getName())
//                .studentEntitySet(team.getStudentEntitySet())
//                .teacherEntitySet(team.getTeacherEntitySet())
//                .build());
//    }
//
//    public void deleteTeamByIdName (String name) {
//        teamRepo.deleteById(name);
//    }
//
//    public List<Team> getAllTeams() {
//        return teamRepo.findAll().stream().map(this::toTeam)
//                .collect(Collectors.toList());
//    }


}