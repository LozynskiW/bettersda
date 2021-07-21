package com.betterSDA.model.entity;

import com.betterSDA.model.ExpirationDate;

import javax.persistence.*;
import java.util.Set;

@Entity
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;

    //Do przemyślenia co nie działa
    //@OneToOne
    //private ExpirationDate expirationDate;

    @OneToMany
    private Set<StudentEntity> studentEntitySet;

    @OneToMany
    private Set<TeacherEntity> teacherEntitySet;

}
