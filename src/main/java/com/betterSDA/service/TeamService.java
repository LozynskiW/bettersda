package com.betterSDA.service;

import com.betterSDA.model.RoleEnum;
import com.betterSDA.model.dto.Person;
import com.betterSDA.model.dto.Team;
import com.betterSDA.model.entity.PersonEntity;
import com.betterSDA.model.entity.TeamEntity;
import com.betterSDA.repo.TeamRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.betterSDA.service.DataConverter.toDto;
import static com.betterSDA.service.DataConverter.toEntity;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepo teamRepo;
    private final PersonService personService;

    public void addTeam(Team team) {
        TeamEntity teamEntity = toEntity(team);
        teamEntity.setTeacherEntitySet(new ArrayList<>());
        teamEntity.setStudentEntitySet(new ArrayList<>());
        teamRepo.save(teamEntity);
    }

    public void updateTeam(Team team) {
        System.out.println("UPDATE");
        System.out.println(team);
        teamRepo.save(toEntity(team));
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

        if (teamEntity.isPresent()) return toDto(teamEntity.get());

        throw new NoSuchElementException("No team found in database");

    }

    public void addPersonToTeam(String teamId, UUID personId) {

        Team team = this.getTeamById(teamId);

        Person person = this.personService.getPersonById(personId);

        if (person.getRole() == RoleEnum.USER) this.addStudentToTeam(team, person);

        else if (person.getRole() == RoleEnum.TEACHER) this.addTeacherToTeam(team, person);

        else throw new IllegalStateException("Person role must either be USER or TEACHER");
    }

    private void addTeacherToTeam(Team team, Person person) {
        team.getTeacherSet().add(person);

        this.updateTeam(team);
    }

    private void addStudentToTeam(Team team, Person person) {
        team.getTeacherSet().add(person);

        this.updateTeam(team);
    }


}