package com.betterSDA.model;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SelectOption {
    private UUID id;
    private String label;
}
