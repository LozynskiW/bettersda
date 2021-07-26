package com.betterSDA.model.entity;

import com.betterSDA.model.ExpirationDate;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;

    //Do przemyślenia co nie działa
    //@OneToOne
    //private ExpirationDate expirationDate;

    @OneToMany
    private Set<PersonEntity> studentEntitySet;

    @OneToMany
    private Set<PersonEntity> teacherEntitySet;

}
