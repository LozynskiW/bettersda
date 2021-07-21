package com.betterSDA.model;

import com.betterSDA.model.entity.AddressEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Person {

    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String email;

    @OneToOne
    protected AddressEntity addressEntity;

}
