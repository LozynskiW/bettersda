package com.betterSDA.service;

import com.betterSDA.model.RoleEnum;
import com.betterSDA.model.dto.Person;
import com.betterSDA.model.entity.PersonEntity;
import com.betterSDA.repo.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public void addPerson(Person person) {

        if (person.getPassword() == null) {
            throw new IllegalStateException("Password cannot be null");
        }

        if (person.getRole() == null) person.setRole(RoleEnum.USER);

        if (person.getTeamID() == null) person.setTeamID("WaitingRoom");

        personRepo.save(PersonEntity.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .phoneNumber(person.getPhoneNumber())
//                .password(passwordEncoder.encode(person.getPassword()))
                .password(person.getPassword())
                .address(person.getAddress())
                .role(person.getRole())
                .teamID(person.getTeamID())
                .build());

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
