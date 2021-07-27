package com.betterSDA.model.dto;

import com.betterSDA.model.RoleEnum;
import com.betterSDA.model.dto.Address;
import com.betterSDA.model.dto.Team;
import lombok.*;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Person {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Address address;
    private String teamID;
    private RoleEnum role;


}
