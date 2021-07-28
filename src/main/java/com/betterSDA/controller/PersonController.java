package com.betterSDA.controller;

import com.betterSDA.model.Country;
import com.betterSDA.model.RoleEnum;
import com.betterSDA.model.dto.Address;
import com.betterSDA.model.dto.Office;
import com.betterSDA.model.dto.Person;

import com.betterSDA.service.AddressService;
import com.betterSDA.service.OfficeService;
import com.betterSDA.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;
    private final AddressService addressService;
    private final OfficeService officeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPerson(@Valid @RequestBody Person person) {

        if (person.getAddress().getId() == null) {

            Long newId = this.personIdGenerator();

            person.getAddress().setId(newId);
            person.setId(newId);

            addressService.addAddress(person.getAddress());

        }

        personService.addPerson(person);
    }

    @GetMapping("/office")
    public void createOffice(){

        Address address = Address.builder()
                .country(Country.POLAND)
                .zipCode("11-500")
                .city("Warszawa")
                .street("Złota")
                .id(0L)
                .build();

        addressService.addAddress(address);

        Person person = Person.builder()
                .phoneNumber("123123123")
                .id(0L)
                .firstName("Software")
                .lastName("Academy")
                .role(RoleEnum.ADMIN)
                .email("sda@academy.pl")
                .address(addressService.getAddressById(0L))
                .teamID("ADMINS")
                .build();

        personService.addPerson(person);

        Office office = Office.builder()
                .id(0L)
                .admins(personService.getPersonById(0L))
                .name("SDA")
                .build();

        officeService.addOffice(office);
    }

    @PutMapping
    public void updatePerson(@Valid @RequestBody Person person) {
        personService.updatePerson(person);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonById(@RequestParam Long id) {
        personService.deletePersonById(id);
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPerson();
    }

    @GetMapping({"/{id}"})
    public Person getPersonById(@PathVariable Long id) {

        return personService.getPersonById(id);
    }

    private Long personIdGenerator() {

        List<Person> personList = personService.getAllPerson();
        Long newId = 1L;

        for (Person person : personList) {
            if (person.getId() > newId) {
                break;
            }
            if (person.getId() <= newId) {
                newId++;
            }
        }
        return newId;
    }

//
//
//    //..............................................................
//
//    @GetMapping
//    public ModelAndView displayAddPersonPage() {
//        ModelAndView mav = new ModelAndView("addPerson");
//        mav.addObject("person", new Person());
//        return mav;
//    }
//
//    @GetMapping("/edit")
//    public ModelAndView displayEditPersonPage(@RequestParam Long id) {
//        ModelAndView mav = new ModelAndView("addPerson");
//        mav.addObject("person", personService.getPersonById(id));
//        return mav;
//    }
//
//    @PostMapping
//    public RedirectView handleAddPerson(@ModelAttribute("person") Person person) {
//        if (person.getId() == null) {
//            personService.addPerson(person);
//        } else {
//            personService.updatePerson(person);
//        }
//
//        return new RedirectView("/api");
//    }
//
//    @GetMapping("/all")
//    public ModelAndView displayAllPersons() {
//        ModelAndView mav = new ModelAndView("persons");
//        mav.addObject("persons", personService.getAllPerson());
//        return mav;
//    }
}
