package com.betterSDA.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressValidator implements ConstraintValidator<Address, com.betterSDA.model.dto.Address> {

    @Override
    public boolean isValid(com.betterSDA.model.dto.Address address, ConstraintValidatorContext constraintValidatorContext) {

        //Default value
        if (address.getCity() == null &&
            address.getCountry() == null &&
            address.getStreet() == null &&
            address.getZipCode() == null) {
            return true;
        }
        //Created value
        if (address.getId() != null &&
            address.getCity() != null &&
            address.getCountry() != null &&
            address.getStreet() != null &&
            address.getZipCode() != null) {
            return true;
        }

        return false;
    }
}
