package com.betterSDA.model.dto;

import com.betterSDA.model.RoleEnum;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;
//@inheritance !!!

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Person {

    private UUID id;
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Length(max = 9, min = 9)
    private String phoneNumber;

    @NotBlank
    @Email
    private String email;

    private Address address = new Address();

    private String teamID;

    @NotNull
    private RoleEnum role;

//    @NotBlank
    private String password;

}
