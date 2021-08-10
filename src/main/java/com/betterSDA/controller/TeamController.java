package com.betterSDA.controller;

import com.betterSDA.model.dto.Person;
import com.betterSDA.model.dto.Team;
import com.betterSDA.service.PersonService;
import com.betterSDA.service.TeamService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final PersonService personService;
    private  final  PersonController personController;

//    @GetMapping("/")
//    public ModelAndView getAllTeams() {
//        ModelAndView mav = new ModelAndView("teams");
//        mav.addObject("teams", teamService.getAllTeams());
//        return mav;
//    }
//
//    @GetMapping("/{id}")
//    public ModelAndView getTeamById(@PathParam("id") String id) {
//        ModelAndView mav = new ModelAndView("teams");
//        mav.addObject("team", teamService.getTeamById(id));
//        return mav;
//    }
//
//    @PostMapping
//    public RedirectView addTeam(Team team) {
//        teamService.addTeam(team);
//        return new RedirectView("/");
//    }



    @GetMapping("/addUser/{personId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAndView displayAddPersonToTeamView(@PathVariable String personId) {
        ModelAndView mav = new ModelAndView("groupListForAdding");
        mav.addObject("teams", teamService.getAllTeams());
        mav.addObject("personId", personId);
        return mav;
    }


//    @PostMapping("/test")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addTeam() {
//
//        teamService.addTeam();
//    }

    @PostMapping("/addUser/{personId}")
    public RedirectView addPersonToTeam(@Valid @RequestBody String teamId, @PathVariable String personId) {
        teamService.addPersonToTeam(teamId.split("=")[1], UUID.fromString(personId));

        return new RedirectView("/api/person/all");
    }

    @GetMapping("/removeUser/{personId}")
    public RedirectView removePersonFromTeam(@Valid @PathVariable String personId) {
        teamService.removePersonFromTeam(UUID.fromString(personId));

        return new RedirectView("/api/person/all");
    }


    @PutMapping("/test")
    public void updateTeam(@Valid @RequestBody Team team) {
        teamService.updateTeam(team);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeamById(@RequestParam String id) {
        teamService.deleteTeamByIdName(id);
    }

    @GetMapping("/test/all")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/test/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Team getTeamById(@PathVariable String id) {
        return teamService.getTeamById(id);
    }

    @GetMapping("/teamStudents/{id}")
    public Set<Person> getTeamStudents(@PathVariable String id) {

        return teamService.getAllStudentsForTeam(id);
    }

    @GetMapping("/teamTeachers/{id}")
    public Set<Person> getTeamTeachers(@PathVariable String id) {

        return teamService.getAllTeachersForTeam(id);
    }

//    @GetMapping("/all")
//    public ModelAndView displayAllTeams() {
//        ModelAndView mav = new ModelAndView("groupList");
//        mav.addObject("teams", teamService.getAllTeams());
//        return mav;
//    }

    @GetMapping("/add")
    public ModelAndView displayAddTeamForm() {
        return new ModelAndView("addGroup");
    }

    //-------------------------------------------------------------------------

    @GetMapping("/all")
    public ModelAndView displayAllTeams() {
        ModelAndView mav = new ModelAndView("teamList");
        mav.addObject("teams", teamService.getAllTeams());
        mav.addObject("team", new Team());
        return mav;
    }

    @GetMapping("/addPerson/{id}")
//    public ModelAndView displayAddPersonToTeamPage(@RequestParam String teamID) {
    public ModelAndView displayAddPersonToTeamPage(@PathVariable String id) {

        List<String> list = new ArrayList<>();

        ModelAndView mav = new ModelAndView("addPersonToTeam");
        mav.addObject("team", id);
        mav.addObject("persons", personService.getAllPerson());
        mav.addObject("list", list);
//                .stream()
//                .map(person -> SelectOption.builder()
//                        .id(person.getId())
//                        .label(person.getFirstName() + " " + person.getLastName()).build())
//                .collect(Collectors.toList()));

        System.out.println("1   " + teamService.getTeamById(id));

        return mav;
    }

    @PostMapping("/put/{teamId}")
    @ResponseStatus(HttpStatus.CREATED)
    public RedirectView handleAddPersonToTeam(@Valid @PathVariable String teamId, List<String> personsId) {
//    public RedirectView handleAddPersonToTeam(@Valid @RequestBody String teamId, @PathVariable String personId) {

        System.out.println("2   " + teamId + "   //   " + personsId);

        teamService.addPersonsToTeam2(teamId, personsId);

        return new RedirectView("/api/team/all");
    }

    @PostMapping
    public RedirectView handleAddTeam(@Valid Team team) {
        if (team.getName() == null) {
            teamService.addTeam(team);
        } else {
            teamService.updateTeam(team);
        }

        return new RedirectView("/api/team/all");
    }

    @GetMapping("/edit")
    public ModelAndView displayEditTeamPage(@RequestParam String name) {
        ModelAndView mav = new ModelAndView("teamList");
        mav.addObject("team", teamService.getTeamById(name));
        return mav;
    }

}
