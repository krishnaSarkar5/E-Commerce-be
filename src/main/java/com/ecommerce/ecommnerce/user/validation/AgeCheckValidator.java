package com.ecommerce.ecommnerce.user.validation;

import com.ecommerce.ecommnerce.user.customannotations.AgeCheck;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;


public class AgeCheckValidator implements ConstraintValidator<AgeCheck, LocalDate> {
    private int minimumAge;

    @Override
    public void initialize(AgeCheck constraintAnnotation) {
        minimumAge = constraintAnnotation.minAge();
    }

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null) {
            return false;
        }

        LocalDate today = LocalDate.now();
        Period period = Period.between(dateOfBirth, today);

        return period.getYears() >= minimumAge;
    }
}