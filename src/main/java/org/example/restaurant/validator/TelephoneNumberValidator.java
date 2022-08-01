package org.example.restaurant.validator;

import org.example.restaurant.util.Util;
import org.example.restaurant.validator.annotation.TelephoneNumberConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelephoneNumberValidator implements
        ConstraintValidator<TelephoneNumberConstraint, String> {

    @Override
    public void initialize(TelephoneNumberConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String telephone,
                           ConstraintValidatorContext cxt) {
        if(telephone != null) {
            return Util.checkRuTelephone(telephone);
        }
        return false;
    }

}