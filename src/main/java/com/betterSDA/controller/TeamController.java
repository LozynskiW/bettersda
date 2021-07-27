package com.betterSDA.controller;

import com.betterSDA.model.dto.Team;
import com.betterSDA.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

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




    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTeam(@Valid @RequestBody Team team) {
        teamService.addTeam(team);
    }

    @PutMapping
    public void updateTeam(@Valid @RequestBody Team team) {
        teamService.updateTeam(team);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeamById(@RequestParam String id) {
        teamService.deleteTeamByIdName(id);
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable String id) {
        return teamService.getTeamById(id);
    }


}
