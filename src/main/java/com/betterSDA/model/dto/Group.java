package com.betterSDA.model.dto;

import com.betterSDA.model.ExpirationDate;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {

    private String name;

    private ExpirationDate expirationDate;

    private Set<Person> studentSet;

    private Set<Person> teacherSet;
}
