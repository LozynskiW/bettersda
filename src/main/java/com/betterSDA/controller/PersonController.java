package com.betterSDA.controller;

import com.betterSDA.model.dto.Person;

import com.betterSDA.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
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
    public String handleAddPerson(@Valid @ModelAttribute("person") Person person, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "redirect:/api/person";
        }

        if (person.getId() == null) {
            personService.addPerson(person);
        } else {
            personService.updatePerson(person);
        }
        return "redirect:/api/person/all";
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
