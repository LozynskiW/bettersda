package com.betterSDA.model.dto;

import com.betterSDA.model.ExpirationDate;
import com.betterSDA.model.entity.StudentEntity;
import com.betterSDA.model.entity.TeacherEntity;
import lombok.*;

import javax.persistence.Id;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {

    private String name;

    //private ExpirationDate expirationDate;

    private Set<StudentEntity> studentEntitySet;

    private Set<TeacherEntity> teacherEntitySet;
}
