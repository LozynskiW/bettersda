package com.betterSDA.model.validator;

import com.betterSDA.model.dto.Address;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressExistenceValidator implements ConstraintValidator<AddressExistence, com.betterSDA.model.dto.Address> {
    @Override
    public boolean isValid(Address address, ConstraintValidatorContext constraintValidatorContext) {
        return address.getCity() != null ||
                address.getCountry() != null ||
                address.getStreet() != null ||
                address.getZipCode() != null;
    }
}
