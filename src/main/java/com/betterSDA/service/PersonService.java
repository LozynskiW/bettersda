package com.betterSDA.service;

import com.betterSDA.model.dto.Address;
import com.betterSDA.model.dto.Person;
import com.betterSDA.model.entity.PersonEntity;
import com.betterSDA.repo.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.betterSDA.service.DataConverter.toDto;
import static com.betterSDA.service.DataConverter.toEntity;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepo personRepo;

    public void addPerson(Person person) {
        System.out.println("addPerson");
        System.out.println(person);
        personRepo.save(toEntity(person));
    }

    public void updatePerson(Person person) {
        personRepo.save(toEntity(person));
    }

    public void deletePersonById (UUID id) {

        personRepo.deleteById(id);
    }

    public List<Person> getAllPerson() {
        return personRepo.findAll().stream().map(DataConverter::toDto).collect(Collectors.toList());
    }

    public Person getPersonById(UUID id) {

        Optional<PersonEntity> personEntity = personRepo.findById(id);

        if (personEntity.isPresent()) {
            return toDto(personEntity.get());
        }

        throw new NoSuchElementException("No such user in database");
    }

}
