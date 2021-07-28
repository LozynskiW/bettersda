package com.betterSDA.model.entity;

import com.betterSDA.model.dto.Team;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OfficeEntity {

    @Id
    private Long id;

    private String name;

    @OneToOne
    private PersonEntity admins;
}
