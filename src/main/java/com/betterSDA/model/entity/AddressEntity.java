package com.betterSDA.model.entity;

import com.betterSDA.model.Country;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AddressEntity {

    @Id
    private Long id;
    private String street;
    private String city;
    private String zipCode;
    @Enumerated(EnumType.STRING)
    private Country country;
}
