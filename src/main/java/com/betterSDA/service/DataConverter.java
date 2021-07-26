package com.betterSDA.service;

import com.betterSDA.model.dto.Person;
import com.betterSDA.model.entity.PersonEntity;

public class DataConverter {

    public static PersonEntity toEntity(Person person) {

        return PersonEntity.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .phoneNumber(person.getPhoneNumber())
                .build();
    }

    public static Person toDto(PersonEntity personEntity) {
        return Person.builder()
                .id(personEntity.getId())
                .firstName(personEntity.getFirstName())
                .lastName(personEntity.getLastName())
                .email(personEntity.getEmail())
                .phoneNumber(personEntity.getPhoneNumber())
                .build();
    }
}
