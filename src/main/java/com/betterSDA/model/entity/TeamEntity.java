package com.betterSDA.model.entity;

import com.betterSDA.model.ExpirationDate;
import com.betterSDA.model.dto.Person;
import com.betterSDA.service.MyGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Required;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "teams")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @GenericGenerator(
            name = "book_seq",
            strategy = "com.betterSDA.service.MyGenerator",
            parameters = {
                    @Parameter(name = MyGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = MyGenerator.VALUE_PREFIX_PARAMETER, value = "SDA_"),
                    @Parameter(name = MyGenerator.NUMBER_FORMAT_PARAMETER, value = "%02d") })
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_team_id")
    private Set<PersonEntity> studentEntitySet = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_team_id")
    private Set<PersonEntity> teacherEntitySet = new HashSet<>();

}
