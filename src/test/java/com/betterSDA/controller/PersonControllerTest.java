package com.betterSDA.controller;

import com.betterSDA.DemoApplication;
import com.betterSDA.model.Country;
import com.betterSDA.model.RoleEnum;
import com.betterSDA.model.dto.Address;
import com.betterSDA.model.dto.Office;
import com.betterSDA.model.dto.Person;
import com.betterSDA.service.OfficeService;
import com.betterSDA.service.PersonService;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {DemoApplication.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PersonControllerTest {

    private MockMvc mockMvc;

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

    }

    @Test
    public void shouldAddPerson() throws Exception {

        //given

        String firstName = "John";
        String lastName = "Carpenter";
        String email = "john.carpenter@thing.com";
        String phoneNumber = "111222333";
        String street = "złota44";

        Address address = new Address();
        address.setStreet(street);
        address.setCountry(Country.POLAND);

        Person personToAdd = Person.builder()
                .id(UUID.randomUUID())
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .role(RoleEnum.USER)
                .address(address)
                .password("12345")
                .build();

        //when

        mockMvc.perform(
                post("/api/person")
                .content(toJson(personToAdd))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                //.andExpect(status().isCreated())
                .andDo(mvcResult -> System.out.println(mvcResult.getRequest().getCharacterEncoding()))
                .andDo(mvcResult -> System.out.println(mvcResult.getRequest().getRequestURL().toString()))
                .andDo(mvcResult -> System.out.println(mvcResult.getRequest().getContentAsString()))
                .andDo(mvcResult -> System.out.println("RES:"+mvcResult.getResponse().getContentAsString()));

        //then

        System.out.println(personService.getAllPerson());
        Assert.assertEquals(1, personService.getAllPerson().size());
        Assert.assertEquals(personToAdd.getPhoneNumber(), personService.getAllPerson().get(0).getPhoneNumber());


    }

    @Test
    public void shouldAddAndReturnPersonEntity() throws Exception {

        //given

        String firstName = "John";
        String lastName = "Carpenter";
        String email = "john.carpenter@thing.com";
        String phoneNumber = "111222333";
        String street = "złota 44";

        Address address = new Address();
        address.setStreet(street);
        address.setCountry(Country.POLAND);

        Person personToAdd = Person.builder()
                .id(UUID.randomUUID())
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .role(RoleEnum.USER)
                .address(address)
                .build();

        //when

        mockMvc.perform(
                post("/api/person/add")
                        .content(toJson(personToAdd))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        //then

        Person personFromDb = personService.getAllPerson().get(0);

        mockMvc.perform(get("/api/person/{id}", personFromDb.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address.street").value(street))
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.lastName").value(lastName));

    }

    @Test
    public void shouldAddAndUpdatePersonEntity() throws Exception {

        //given

        String firstName = "John";
        String lastName = "Carpenter";
        String email = "john.carpenter@thing.com";
        String phoneNumber = "111222333";
        String street = "złota 44";

        Address address = new Address();
        address.setStreet(street);
        address.setCountry(Country.POLAND);

        Person personToAdd = Person.builder()
                .id(UUID.randomUUID())
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .role(RoleEnum.USER)
                .build();

        //when

        mockMvc.perform(
                post("/api/person/add")
                        .content(toJson(personToAdd))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        Person personFromDB = personService.getAllPerson().get(0);

        personFromDB.setAddress(address);
        personFromDB.setTeamID("directors");

        mockMvc.perform(
                post("/api/person/add")
                        .content(toJson(personFromDB))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        //then

        mockMvc.perform(get("/api/person/test/all", personToAdd.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$.[0].address.street").value(street));

    }

    @Test
    public void shouldDeletePerson() throws Exception {
        //given

        String firstName = "John";
        String lastName = "Carpenter";
        String email = "john.carpenter@thing.com";
        String phoneNumber = "111222333";
        String street = "złota 44";

        Address address = new Address();
        address.setStreet(street);
        address.setCountry(Country.POLAND);

        Person personToAdd = Person.builder()
                .id(UUID.randomUUID())
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .role(RoleEnum.USER)
                .build();

        //when

        mockMvc.perform(
                post("/api/person/add")
                        .content(toJson(personToAdd))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        Person personFromDBAfterAdding = personService.getAllPerson().get(0);

        mockMvc.perform(
                delete("/api/person?id={id}", personFromDBAfterAdding.getId()))
                .andExpect(status().isNoContent());

        //then

        mockMvc.perform(get("/api/person/test/all", personToAdd.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").isEmpty());
    }
}