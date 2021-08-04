package com.betterSDA.controller;

import com.betterSDA.model.dto.Person;

import com.betterSDA.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addPerson(@Valid @RequestBody Person person) {
//
////        if (person.getAddress().getId() == null) {
////
////            Long newId = this.personIdGenerator();
////
////            person.getAddress().setId(newId);
////            person.setId(newId);
////
////            addressService.addAddress(person.getAddress());
////
////        }
//
//        personService.addPerson(person);
//    }

//    @PutMapping
//    public void updatePerson(@Valid @RequestBody Person person) {
//        personService.updatePerson(person);
//    }
//
//    @DeleteMapping()
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deletePersonById(@RequestParam UUID id) {
//        personService.deletePersonById(id);
//    }
//
////    @GetMapping
////    public List<Person> getAllPersons() {
////        return personService.getAllPerson();
////    }
//
//    @GetMapping({"/{id}"})
//    public Person getPersonById(@PathVariable UUID id) {
//
//        return personService.getPersonById(id);
//    }

//    //..............................................................
//
    @GetMapping
    public ModelAndView displayAddPersonPage() {
        ModelAndView mav = new ModelAndView("addPerson");
        mav.addObject("person", new Person());
        return mav;
    }

    @GetMapping("/edit")
    public ModelAndView displayEditPersonPage(@RequestParam UUID id) {
        ModelAndView mav = new ModelAndView("addPerson");
        mav.addObject("person", personService.getPersonById(id));
        return mav;
    }

    @PostMapping
    public RedirectView handleAddPerson(@Valid Person person) {
        if (person.getId() == null) {
            personService.addPerson(person);
        } else {
            personService.updatePerson(person);
        }

        return new RedirectView("/");
    }

    @GetMapping("/test/all")
    public List<Person> testGetAllPerson() {
        return personService.getAllPerson();
    }

    @GetMapping("/all")
    public ModelAndView displayAllPersons() {
        ModelAndView mav = new ModelAndView("personsList");
        mav.addObject("persons", personService.getAllPerson());
        return mav;
    }
}
