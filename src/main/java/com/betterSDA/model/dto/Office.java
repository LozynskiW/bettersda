package com.betterSDA.model.dto;

import java.util.Set;

public class Office {

    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private Set<Person> admins;
}
