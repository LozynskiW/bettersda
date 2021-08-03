package com.betterSDA.controller;

import com.betterSDA.DemoApplication;
import com.betterSDA.model.Country;
import com.betterSDA.model.RoleEnum;
import com.betterSDA.model.dto.Address;
import com.betterSDA.model.dto.Person;
import com.betterSDA.model.dto.Team;
import com.betterSDA.service.OfficeService;
import com.betterSDA.service.PersonService;
import com.betterSDA.service.TeamService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.assertj.core.util.Sets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {DemoApplication.class})
public class TeamControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PersonService personService;

    @Autowired
    private OfficeService officeService;

    private static String toJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(obj);
    }

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        officeService.deleteOfficeForTest();

        List<Person> personToDeleteList = personService.getAllPerson();

        personToDeleteList.forEach(person -> personService.deletePersonById(person.getId()));

        List<Team> teamsToDeleteList = teamService.getAllTeams();

        teamsToDeleteList.forEach(team -> teamService.deleteTeamByIdName(team.getName()));

    }

    @Test
    public void shouldAddTeam() throws Exception {

        //given

        String team1 = "SDA_01";
        String team2 = "SDA_02";

        Team teamToAdd1 = Team.builder().name(team1).build();
        Team teamToAdd2 = Team.builder().name(team2).build();

        //when

        mockMvc.perform(
                post("/api/team")
                        .content(toJson(teamToAdd1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform(
                post("/api/team")
                        .content(toJson(teamToAdd2))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        //then

        Team teamFromDB1 = teamService.getAllTeams().get(0);
        Team teamFromDB2 = teamService.getAllTeams().get(1);

        Assert.assertEquals(teamToAdd1.getName(), teamFromDB1.getName());
        Assert.assertEquals(teamToAdd2.getName(), teamFromDB2.getName());
    }

    @Test
    public void shouldUpdateTeam() throws Exception {

        //given

        String teamName = "SDA_01";

        Person student1 = Person.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .phoneNumber("111222333")
                .email("das@dasdas.com")
                .role(RoleEnum.USER)
                .build();
        Person student2 = Person.builder()
                .firstName("Marek")
                .lastName("Nowak")
                .phoneNumber("111222333")
                .email("das@dasdas.com")
                .role(RoleEnum.USER)
                .build();
        Person teacher1 = Person.builder()
                .firstName("Janusz")
                .lastName("Krocz")
                .phoneNumber("111222333")
                .email("das@dasdas.com")
                .role(RoleEnum.TEACHER)
                .build();
        Person teacher2 = Person.builder()
                .firstName("Michał")
                .lastName("Lebioda")
                .phoneNumber("111222333")
                .email("das@dasdas.com")
                .role(RoleEnum.TEACHER)
                .build();

        personService.addPerson(student1);
        personService.addPerson(student2);
        personService.addPerson(student1);
        personService.addPerson(student2);

        Team teamBeforeUpdate = Team.builder()
                .name(teamName)
                .teacherSet(personService.getAllPerson().stream().filter(p -> p.getRole().equals(RoleEnum.TEACHER)).collect(Collectors.toSet()))
                .studentSet(personService.getAllPerson().stream().filter(p -> p.getRole().equals(RoleEnum.USER)).collect(Collectors.toSet()))
                .build();

        teacher1.setFirstName("Tomasz");
        student1.setFirstName("Artur");
        personService.updatePerson(student1);
        personService.updatePerson(teacher1);

        Team teamAfterUpdate = Team.builder()
                .name(teamName)
                .teacherSet(personService.getAllPerson().stream().filter(p -> p.getRole().equals(RoleEnum.TEACHER)).collect(Collectors.toSet()))
                .studentSet(personService.getAllPerson().stream().filter(p -> p.getRole().equals(RoleEnum.USER)).collect(Collectors.toSet()))
                .build();


        //when

        mockMvc.perform(
                post("/api/team")
                        .content(toJson(teamBeforeUpdate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        Team teamFromDB = teamService.getAllTeams().get(0);

        System.out.println(teamFromDB.getTeacherSet());
        System.out.println(Arrays.toString(teamFromDB.getTeacherSet().toArray()));
        System.out.println(Arrays.toString(teamFromDB.getTeacherSet().toArray()));

        mockMvc.perform(
                put("/api/team")
                        .content(toJson(teamAfterUpdate))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        //then

        teamFromDB = teamService.getAllTeams().get(0);

        System.out.println(Arrays.toString(teamFromDB.getTeacherSet().toArray()));
        System.out.println(Arrays.toString(teamFromDB.getTeacherSet().toArray()));

        Assert.assertEquals(teamAfterUpdate.getName(), teamFromDB.getName());
        Assert.assertTrue(teamAfterUpdate.getTeacherSet().contains(teacher1));
        Assert.assertTrue(teamAfterUpdate.getTeacherSet().contains(teacher2));
        Assert.assertTrue(teamAfterUpdate.getTeacherSet().contains(student1));
        Assert.assertTrue(teamAfterUpdate.getTeacherSet().contains(student2));

    }

    @Test
    public void shouldDeleteTeamById() throws Exception {
    }

    @Test
    public void shouldGetAllTeams() throws Exception {
    }

    @Test
    public void shouldGetTeamById() throws Exception {
    }
}