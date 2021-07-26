package com.betterSDA.model.entity;

import com.betterSDA.model.dto.Group;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OfficeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;

    @OneToMany
    private Set<PersonEntity> admins;
}
