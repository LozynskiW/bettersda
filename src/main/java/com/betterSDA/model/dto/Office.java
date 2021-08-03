package com.betterSDA.model.dto;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Office {

    private UUID id;
    private String name;
    private Person admins;
}
