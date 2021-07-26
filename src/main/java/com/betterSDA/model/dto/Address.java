package com.betterSDA.model.dto;

import com.betterSDA.model.Country;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    private Long id;

    private String street;

    private String city;

    private String zipCode;

    private Country country;
}
