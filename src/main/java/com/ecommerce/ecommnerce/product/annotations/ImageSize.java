package com.ecommerce.ecommnerce.product.annotations;

import com.ecommerce.ecommnerce.product.validator.ImageSizeValidator;
import com.ecommerce.ecommnerce.user.validation.AgeCheckValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageSizeValidator.class)
public @interface ImageSize {
    String message() default "Image should be less than {value} MB";

     int minSize() default 1;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
