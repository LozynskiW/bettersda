package com.betterSDA.controller;

import com.betterSDA.DemoApplication;
import com.betterSDA.model.RoleEnum;
import com.betterSDA.model.dto.Person;
import com.betterSDA.model.dto.Team;
import com.betterSDA.service.OfficeService;
import com.betterSDA.service.PersonService;
import com.betterSDA.service.TeamService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {DemoApplication.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

    private void addUsersForTestingToDB() throws Exception {
        String teamName = "SDA_01";

        Person student1 = Person.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .phoneNumber("111222333")
                .email("das@dasdas.com")
                .role(RoleEnum.USER)
                .password("12345")
                .teamID("test")
                .build();
        Person student2 = Person.builder()
                .firstName("Marek")
                .lastName("Nowak")
                .phoneNumber("111222333")
                .email("das@dasdas.com")
                .role(RoleEnum.USER)
                .password("12345")
                .teamID("test")
                .build();
        Person teacher1 = Person.builder()
                .firstName("Janusz")
                .lastName("Krocz")
                .phoneNumber("111222333")
                .email("das@dasdas.com")
                .role(RoleEnum.TEACHER)
                .password("12345")
                .teamID("test")
                .build();
        Person teacher2 = Person.builder()
                .firstName("Michał")
                .lastName("Lebioda")
                .phoneNumber("111222333")
                .email("das@dasdas.com")
                .role(RoleEnum.TEACHER)
                .password("12345")
                .teamID("test")
                .build();

        personService.addPerson(student1);
        personService.addPerson(student2);
        personService.addPerson(teacher1);
        personService.addPerson(teacher2);

        Team teamBeforeAddingPersons = Team.builder()
                .name(teamName)
                .build();

        mockMvc.perform(
                post("/api/team")
                        .content(toJson(teamBeforeAddingPersons))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
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
    public void shouldAddPersonsToTeam() throws Exception {

        //given
        addUsersForTestingToDB();

        //when

        Team teamFromDB = teamService.getAllTeams().get(0);

        Person personToAdd1 = personService.getAllPerson().get(0);
        Person personToAdd2 = personService.getAllPerson().get(1);
        Person personToAdd3 = personService.getAllPerson().get(2);
        Person personToAdd4 = personService.getAllPerson().get(3);

        mockMvc.perform(
                put("/api/team/addUser/{personId}", personToAdd1.getId())
                        .content(teamFromDB.getName())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8));

        mockMvc.perform(
                put("/api/team/addUser/{personId}", personToAdd2.getId())
                        .content(teamFromDB.getName())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8));

        mockMvc.perform(
                put("/api/team/addUser/{personId}", personToAdd3.getId())
                        .content(teamFromDB.getName())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8));

        mockMvc.perform(
                put("/api/team/addUser/{personId}", personToAdd4.getId())
                        .content(teamFromDB.getName())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8));

        //then

        mockMvc.perform(
                get("/api/team/teamStudents/{id}", teamFromDB.getName()));

        mockMvc.perform(get("/api/person/test/all"));

        Set<Person> studentSet = teamService.getAllStudentsForTeam(teamFromDB.getName());

        Set<Person> teacherSet = teamService.getAllTeachersForTeam(teamFromDB.getName());


        Assert.assertEquals(2, studentSet.size());
        Assert.assertEquals(2, teacherSet.size());

    }

    @Test
    public void shouldRemovePersonFromTeam() throws Exception {

        //given
        addUsersForTestingToDB();

        //when

        Team teamFromDB = teamService.getAllTeams().get(0);

        Person personToAdd1 = personService.getAllPerson().get(0);
        Person personToAdd2 = personService.getAllPerson().get(1);
        Person personToAdd3 = personService.getAllPerson().get(2);
        Person personToAdd4 = personService.getAllPerson().get(3);

        mockMvc.perform(
                put("/api/team/addUser/{personId}", personToAdd1.getId())
                        .content(teamFromDB.getName())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8));

        mockMvc.perform(
                put("/api/team/addUser/{personId}", personToAdd2.getId())
                        .content(teamFromDB.getName())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8));

        mockMvc.perform(
                put("/api/team/addUser/{personId}", personToAdd3.getId())
                        .content(teamFromDB.getName())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8));

        mockMvc.perform(
                put("/api/team/addUser/{personId}", personToAdd4.getId())
                        .content(teamFromDB.getName())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8));

        //then

        mockMvc.perform(
                delete("/api/team/removeUser/{personId}", personToAdd2.getId())
                        .content(teamFromDB.getName())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8));

        mockMvc.perform(
                delete("/api/team/removeUser/{personId}", personToAdd4.getId())
                        .content(teamFromDB.getName())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8));

        mockMvc.perform(
                get("/api/team/teamStudents/{id}", teamFromDB.getName()));

        mockMvc.perform(get("/api/person/test/all"));

        Set<Person> studentSet = teamService.getAllStudentsForTeam(teamFromDB.getName());

        Set<Person> teacherSet = teamService.getAllTeachersForTeam(teamFromDB.getName());

        Assert.assertEquals(studentSet.size(), 1);
        Assert.assertEquals(teacherSet.size(), 1);

    }

    @Test
    public void shouldDeleteTeamById() throws Exception {
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

        mockMvc.perform(
                delete("/api/team")
                        .param("id",teamToAdd2.getName())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        Assert.assertEquals(1, teamService.getAllTeams().size());
        Assert.assertNotNull(teamService.getAllTeams().get(0));
        Assert.assertEquals(teamToAdd1.getName(), teamService.getAllTeams().get(0).getName());
    }

    @Test
    public void shouldGetAllTeams() throws Exception {

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

        Assert.assertEquals(2, teamService.getAllTeams().size());
        Assert.assertNotNull(teamService.getAllTeams().get(0));
        Assert.assertNotNull(teamService.getAllTeams().get(1));
        Assert.assertEquals(teamToAdd1.getName(), teamService.getAllTeams().get(0).getName());
        Assert.assertEquals(teamToAdd2.getName(), teamService.getAllTeams().get(1).getName());
    }

    @Test
    public void shouldGetTeamById() throws Exception {

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

        //System.out.println(Arrays.toString(teamService.getAllTeams().toArray()));
        mockMvc.perform(
                get("/api/team/{id}", team2)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

    }
}