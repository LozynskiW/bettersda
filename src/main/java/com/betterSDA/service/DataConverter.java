package com.betterSDA.service;

import com.betterSDA.model.dto.Person;
import com.betterSDA.model.entity.PersonEntity;

public class DataConverter {

    private PersonEntity ToEntity(Person person) {

        return PersonEntity.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .phoneNumber(person.getPhoneNumber())
                .build();
    }

    private Person ToDto(PersonEntity personEntity) {
        return Person.builder()
                .id(personEntity.getId())
                .firstName(personEntity.getFirstName())
                .lastName(personEntity.getLastName())
                .email(personEntity.getEmail())
                .phoneNumber(personEntity.getPhoneNumber())
                .build();
    }
}
