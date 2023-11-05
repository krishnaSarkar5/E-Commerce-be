package com.ecommerce.ecommnerce.auth.service;


import com.ecommerce.ecommnerce.auth.dto.request.LoginRequestDto;
import com.ecommerce.ecommnerce.common.response.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<CommonResponse> login(LoginRequestDto requestDto);

    ResponseEntity<CommonResponse> logout();

}
