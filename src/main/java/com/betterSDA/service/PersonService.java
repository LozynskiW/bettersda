package com.betterSDA.service;

import com.betterSDA.model.dto.Address;
import com.betterSDA.model.dto.Person;
import com.betterSDA.repo.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepo personRepo;

    public void addPerson(Person person) {

    }

    public void updatePerson(Person person) {

    }

    public void deletePersonById (Long id) {

        personRepo.deleteById(id);
    }

    public List<Person> getAllPerson() {
        return null;
    }

    public Address getPersonById(Long id) {

        return null;
    }

}
