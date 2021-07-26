package com.betterSDA.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Person {

    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String email;

}
