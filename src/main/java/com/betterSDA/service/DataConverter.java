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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

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
            personEntity.setPassword(person.getPassword());
            personEntity.setTeamID(person.getTeamID());
            personEntity.setRole(person.getRole());


        } catch (NullPointerException ex) {
            System.err.println("No byczku coś się zepsuło");
        }


        if (person.getAddress() != null) personEntity.setAddress(person.getAddress());

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
            person.setPassword(personEntity.getPassword());
            person.setTeamID(personEntity.getTeamID());
            person.setRole(personEntity.getRole());

        } catch (NullPointerException ex) {
            System.err.println("No byczku coś się zepsuło");
        }

        if (personEntity.getAddress() != null) person.setAddress(personEntity.getAddress());
        else person.setAddress(null);

        if (personEntity.getRole() == RoleEnum.ADMIN || personEntity.getRole() == RoleEnum.TEACHER || personEntity.getRole() == RoleEnum.USER) person.setRole(personEntity.getRole());
        else person.setRole(RoleEnum.USER);

        return person;

    }

    public static Team toDto(TeamEntity teamEntity) {
        try {
            return Team.builder()
                    .name(teamEntity.getName())
                    .teacherSet(teamEntity.getTeacherEntitySet().stream().map(DataConverter::toDto).collect(Collectors.toSet()))
                    .studentSet(teamEntity.getStudentEntitySet().stream().map(DataConverter::toDto).collect(Collectors.toSet()))
                    .build();

        } catch (Exception e) {
            return Team.builder()
                    .name(teamEntity.getName())
                    .build();
        }

    }

    public static TeamEntity toEntity(Team team) {
        try {
            return TeamEntity.builder()
                    .name(team.getName())
                    .teacherEntitySet(team.getTeacherSet().stream().map(DataConverter::toEntity).collect(Collectors.toSet()))
                    .studentEntitySet(team.getStudentSet().stream().map(DataConverter::toEntity).collect(Collectors.toSet()))
                    .build();

        } catch (Exception e) {

            return TeamEntity.builder()
                    .name(team.getName())
                    .build();
        }
    }

    public static Office toDto(OfficeEntity officeEntity) {
        return Office.builder()
                .id(officeEntity.getId())
                .name(officeEntity.getName())
                .admins(toDto(officeEntity.getAdmins()))
                .build();
    }

    public static OfficeEntity toEntity(Office office) {
        return OfficeEntity.builder()
                .id(office.getId())
                .name(office.getName())
                .admins(toEntity(office.getAdmins()))
                .build();
    }



}
