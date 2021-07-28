package com.betterSDA.model.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Office {

    private Long id;
    private String name;
    private Person admins;
}
