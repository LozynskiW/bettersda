package com.betterSDA.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Constraint(validatedBy = {AddressValidator.class})
@Target({ElementType.FIELD})
public @interface Address {

    String message() default "Adres pusty, ustawiono na domyślną wartość";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
