package com.betterSDA.model.entity;

import com.betterSDA.model.ExpirationDate;
import lombok.*;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TeamEntity {

    @Id
    @GeneratedValue()
    @NotNull
    private String name;

    @OneToMany
    private Set<PersonEntity> studentEntitySet;

    @OneToMany
    private Set<PersonEntity> teacherEntitySet;

}
