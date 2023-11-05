package com.ecommerce.ecommnerce.user.validation;

import com.ecommerce.ecommnerce.user.customannotations.AgeCheck;
import com.ecommerce.ecommnerce.user.customannotations.Phone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PhoneCheckValidator implements ConstraintValidator<Phone, String> {
    private int minimumAge;

    @Override
    public void initialize(Phone constraintAnnotation) {

    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        String regex = "(0/91)?[7-9][0-9]{9}";
        Pattern p = Pattern.compile(regex);

       if(StringUtils.isEmpty(phone)){
           return true;
       }
        Matcher m = p.matcher(phone);

        return m.matches();
    }
}