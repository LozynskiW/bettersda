package com.betterSDA.model.dto;

import com.betterSDA.model.Country;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Address {

    private String street;

    private String city;

    private String zipCode;

    private Country country;
}
