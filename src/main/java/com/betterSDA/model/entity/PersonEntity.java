package com.betterSDA.model.entity;

import com.betterSDA.model.RoleEnum;
import com.betterSDA.model.dto.Address;
import com.betterSDA.model.dto.Team;
import lombok.*;

import javax.persistence.*;

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

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private RoleEnum role;

    @OneToOne
    protected AddressEntity addressEntity;

    private String teamID;
}
