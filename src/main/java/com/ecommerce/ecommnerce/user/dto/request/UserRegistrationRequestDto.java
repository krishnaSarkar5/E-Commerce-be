package com.ecommerce.ecommnerce.user.dto.request;


import com.ecommerce.ecommnerce.user.customannotations.AgeCheck;
import com.ecommerce.ecommnerce.user.customannotations.Phone;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequestDto {


    @NotEmpty(message = "first name can not be empty")
    private String firstName;

    @NotEmpty(message = "last name can not be empty")
    private String lastName;

    @NotEmpty(message = "email can not be empty")
    @Email(message = "invalid email")
    private String email;

    @NotEmpty(message = "email can not be empty")
//    @Pattern(regexp = "(0/91)?[7-9][0-9]{9}",message = "invalid phone number")
    @Phone
    private String phone;

    @NotEmpty(message = "email can not be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message = "invalid phone number")
    private String password;

    @NotNull(message = "date of birth can not be empty")
    @AgeCheck(message = "Age should be above 18 ",minAge = 18)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dob;

}


