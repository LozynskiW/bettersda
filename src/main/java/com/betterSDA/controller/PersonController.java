package com.betterSDA.controller;

import com.betterSDA.model.dto.Person;

import com.betterSDA.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPerson(@Valid @RequestBody Person person) {
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

    @GetMapping("/{id}")
    public Person getPersonById(Long id) {
        return personService.getPersonById(id);
    }
}
