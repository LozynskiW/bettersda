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

import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepo teamRepo;
    private final PersonService personService;

    public void addTeam(Team team) {
        TeamEntity teamEntity = TeamEntity.builder().build();
        teamEntity.setTeacherEntitySet(new HashSet<>());
        teamEntity.setStudentEntitySet(new HashSet<>());
        teamRepo.save(teamEntity);
    }

    public void updateTeam(Team team) {

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

        Person person;

        try {

            Team team = this.getTeamById(teamId);

            person = this.personService.getPersonById(personId);

            if (person.getRole() == RoleEnum.TEACHER || person.getRole() == RoleEnum.USER) {
                person.setTeamID(team.getName());
                personService.updatePerson(person);
            }
            else throw new IllegalStateException("Role must either be TEACHER or USER");

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void removePersonFromTeam(UUID personId) {

        Person person;

        try {

            person = this.personService.getPersonById(personId);

            if (person.getRole() == RoleEnum.TEACHER || person.getRole() == RoleEnum.USER) {
                person.setTeamID("WaitingRoom");
                personService.updatePerson(person);
            }
            else throw new IllegalStateException("Role must either be TEACHER or USER");

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

    }

    public void addPersonsToTeam(String teamID, String personsUUIDs) {
        Set<Person> person = new HashSet<>();

        try {
//            Team team = this.getTeamById(teamID);


                person.add(personService.getPersonById(UUID.fromString(personsUUIDs)));



        }catch (NoSuchElementException ex) {
            ex.printStackTrace();
        }

        Team team = Team.builder()
                .name(teamID)
                .studentSet(person)
                .build();

        updateTeam(team);

    }    public void addPersonsToTeam2(String team, List<String> personsId) {


        try {
                    Person newPerson = Person.builder()
                    .id(UUID.fromString(personsId.get(0)))
                    .teamID(team)
                    .build();


            personService.updatePerson(newPerson);




        }catch (NoSuchElementException ex) {
            ex.printStackTrace();
        }


    }

    public Set<Person> getAllStudentsForTeam(String teamId) {
        return personService.getAllPerson()
                .stream()
                //.peek(System.out::println)
                .filter(p -> p.getTeamID() == teamId)
                .filter(p -> p.getRole() == RoleEnum.USER)
                .collect(Collectors.toSet());
    }

    public Set<Person> getAllTeachersForTeam(String teamId) {
        return personService.getAllPerson()
                .stream()
                .filter(p -> p.getTeamID() == teamId)
                .filter(p -> p.getRole() == RoleEnum.TEACHER)
                .collect(Collectors.toSet());
    }

}