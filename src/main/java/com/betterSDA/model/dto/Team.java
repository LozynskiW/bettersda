package com.betterSDA.model.dto;

import com.betterSDA.model.ExpirationDate;
import lombok.*;

import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Team {

    private String name;

    //To do
    //private ExpirationDate expirationDate;

    private Set<Person> studentSet = new HashSet<>();

    private Set<Person> teacherSet = new HashSet<>();
}
