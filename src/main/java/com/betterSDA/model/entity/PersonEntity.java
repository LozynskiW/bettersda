package com.betterSDA.model.entity;

import com.betterSDA.model.RoleEnum;
import com.betterSDA.model.dto.Team;
import com.betterSDA.model.validator.Address;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name must not be blank")
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String email;
    @NotNull
    private RoleEnum role;

    @OneToOne
    protected AddressEntity addressEntity = new AddressEntity();

    private String teamID;
}
