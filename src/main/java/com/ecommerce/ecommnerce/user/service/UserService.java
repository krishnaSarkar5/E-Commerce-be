package com.ecommerce.ecommnerce.user.service;

import com.ecommerce.ecommnerce.user.dto.request.AddressAddRequestDto;
import com.ecommerce.ecommnerce.user.dto.request.UserRegistrationRequestDto;
import com.ecommerce.ecommnerce.user.dto.response.UserResponseDto;

public interface UserService {

    UserResponseDto registerUser(UserRegistrationRequestDto requestDto);

    String addAddress(AddressAddRequestDto requestDto);


}
