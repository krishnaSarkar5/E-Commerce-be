package com.ecommerce.ecommnerce.user.dtoconverter;

import com.ecommerce.ecommnerce.common.enums.Status;
import com.ecommerce.ecommnerce.user.Role;
import com.ecommerce.ecommnerce.user.dto.request.UserRegistrationRequestDto;
import com.ecommerce.ecommnerce.user.dto.response.UserResponseDto;
import com.ecommerce.ecommnerce.user.entity.User;

import java.util.ArrayList;

public class UserDtoConverter {

    public static User convertRegistrationPostRequestToEntity(UserRegistrationRequestDto registrationRequestDto){

        return User.builder()
                .firstName(registrationRequestDto.getFirstName())
                .lastName(registrationRequestDto.getLastName())
                .email(registrationRequestDto.getEmail())
                .phone(registrationRequestDto.getPhone())
                .password(registrationRequestDto.getPassword())
                .status(Status.ACTIVE.getValue())
                .role(Role.USER.getValue())
                .dob(registrationRequestDto.getDob())
                .addresses(new ArrayList<>())
                .build();

    }

    public  static UserResponseDto convertUserEntityToUserResponseDto(User user){

        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dob(user.getDob())
                .role(user.getRole())
                .password(user.getPassword())
                .email(user.getEmail())
                .phone(user.getPhone())
                .createdAt(user.getCreateUpdateInformation().getCreatedAt())
                .status(user.getStatus())
                .build();
    }
}
