package com.betterSDA.controller;

import com.betterSDA.model.dto.Address;
import com.betterSDA.model.dto.Person;

import com.betterSDA.service.AddressService;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPerson(@Valid @RequestBody Person person) {
        if (person.getAddress().getId() == null) {
            addressService.addAddress(person.getAddress());
            Address address = addressService.getAllAddresses().get(0);
            person.setAddress(address);
        }
        personService.addPerson(person);
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
