package com.betterSDA.service;

import com.betterSDA.model.dto.Address;
import com.betterSDA.model.dto.Person;
import com.betterSDA.model.dto.Team;
import com.betterSDA.model.entity.AddressEntity;
import com.betterSDA.model.entity.PersonEntity;
import com.betterSDA.model.entity.TeamEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class DataConverter {

    public static PersonEntity toEntity(Person person) {

        return PersonEntity.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .addressEntity(toEntity(person.getAddress()))
                .teamID(person.getTeamID())
                .phoneNumber(person.getPhoneNumber())
                .build();
    }

    public static Person toDto(PersonEntity personEntity) {
        return Person.builder()
                .id(personEntity.getId())
                .firstName(personEntity.getFirstName())
                .lastName(personEntity.getLastName())
                .email(personEntity.getEmail())
                .address(toDto(personEntity.getAddressEntity()))
                .teamID(personEntity.getTeamID())
                .phoneNumber(personEntity.getPhoneNumber())
                .build();
    }

    public static Address toDto(AddressEntity addressEntity) {
        return Address.builder()
                .id(addressEntity.getId())
                .street(addressEntity.getStreet())
                .city(addressEntity.getCity())
                .zipCode(addressEntity.getZipCode())
                .country(addressEntity.getCountry())
                .build();
    }

    public static AddressEntity toEntity(Address address) {
        return AddressEntity.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .zipCode(address.getZipCode())
                .country(address.getCountry())
                .build();
    }

//GÓWNO NIE DZIAŁA MI MÓZG I NIE WIEM JAK TO PRZEKONWERTOWAĆ... LECE NA OGNISKO JUTRO TO ZROBIĘ
// ;)
    public static Team toDto(TeamEntity teamEntity) {
        return Team.builder()
                .name(teamEntity.getName())
                .teacherSet(teamEntity.getTeacherEntitySet().stream().map(DataConverter::toDto).collect(Collectors.toSet()))
                .studentSet(teamEntity.getStudentEntitySet().stream().map(DataConverter::toDto).collect(Collectors.toSet()))
                .build();
    }

    public static TeamEntity toEntity(Team team) {
        return TeamEntity.builder()
                .name(team.getName())
                .teacherEntitySet(team.getTeacherSet().stream().map(DataConverter::toEntity).collect(Collectors.toSet()))
                .studentEntitySet(team.getStudentSet().stream().map(DataConverter::toEntity).collect(Collectors.toSet()))
                .build();
    }



}
