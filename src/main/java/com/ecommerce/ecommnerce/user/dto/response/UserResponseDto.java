package com.ecommerce.ecommnerce.user.dto.response;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

    public Long id;

    private String firstName;

    private String lastName;

    public String email;

    public String phone;

    public  String password;

    @JsonFormat(pattern = "dd-MM-yyyy")
    public LocalDate dob ;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    public LocalDateTime createdAt;

    public Byte status;


    public String role;


}
