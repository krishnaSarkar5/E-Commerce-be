package com.ecommerce.ecommnerce.user.customannotations;

import com.ecommerce.ecommnerce.user.validation.AgeCheckValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeCheckValidator.class)
public @interface AgeCheck {
    String message() default "Age should be at least {value} years";
    int minAge() default 18;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
