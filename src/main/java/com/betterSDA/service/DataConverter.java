package com.betterSDA.service;

import com.betterSDA.model.RoleEnum;
import com.betterSDA.model.dto.Address;
import com.betterSDA.model.dto.Office;
import com.betterSDA.model.dto.Person;
import com.betterSDA.model.dto.Team;
import com.betterSDA.model.entity.AddressEntity;
import com.betterSDA.model.entity.OfficeEntity;
import com.betterSDA.model.entity.PersonEntity;
import com.betterSDA.model.entity.TeamEntity;

import java.util.stream.Collectors;

public class DataConverter {

    public static PersonEntity toEntity(Person person) {

        PersonEntity personEntity = new PersonEntity();

        try {
            personEntity.setId(person.getId());
            personEntity.setEmail(person.getEmail());
            personEntity.setFirstName(person.getFirstName());
            personEntity.setLastName(person.getLastName());
            personEntity.setPhoneNumber(person.getPhoneNumber());
        } catch (NullPointerException ex) {
            System.err.println("No byczku coś się zepsuło");
        }
        if (person.getTeamID() != null) personEntity.setTeamID(person.getTeamID());
        else personEntity.setTeamID("Brak grupy");
        if (person.getAddress() != null) personEntity.setAddressEntity(toEntity(person.getAddress()));
        else personEntity.setAddressEntity(null);
        if (person.getRole() == RoleEnum.ADMIN || person.getRole() == RoleEnum.TEACHER || person.getRole() == RoleEnum.USER) personEntity.setRole(person.getRole());
        else personEntity.setRole(RoleEnum.USER);

        return personEntity;
    }

    public static Person toDto(PersonEntity personEntity) {

        Person person = new Person();

        try {
            person.setId(personEntity.getId());
            person.setEmail(personEntity.getEmail());
            person.setFirstName(personEntity.getFirstName());
            person.setLastName(personEntity.getLastName());
            person.setPhoneNumber(personEntity.getPhoneNumber());
        } catch (NullPointerException ex) {
            System.err.println("No byczku coś się zepsuło");
        }
        if (personEntity.getTeamID() != null) person.setTeamID(personEntity.getTeamID());
        else person.setTeamID("Brak grupy");
        if (personEntity.getAddressEntity() != null) person.setAddress(toDto(personEntity.getAddressEntity()));
        else person.setAddress(null);
        if (personEntity.getRole() == RoleEnum.ADMIN || personEntity.getRole() == RoleEnum.TEACHER || personEntity.getRole() == RoleEnum.USER) person.setRole(personEntity.getRole());
        else person.setRole(RoleEnum.USER);

        return person;

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

    public static Office toDto(OfficeEntity officeEntity) {
        return Office.builder()
                .name(officeEntity.getName())
                .admins(toDto(officeEntity.getAdmins()))
                .build();
    }

    public static OfficeEntity toEntity(Office office) {
        return OfficeEntity.builder()
                .name(office.getName())
                .admins(toEntity(office.getAdmins()))
                .build();
    }



}
