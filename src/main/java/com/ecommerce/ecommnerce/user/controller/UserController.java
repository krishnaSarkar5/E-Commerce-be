package com.ecommerce.ecommnerce.user.controller;

import com.ecommerce.ecommnerce.common.response.CommonResponse;
import com.ecommerce.ecommnerce.user.dto.request.AddressAddRequestDto;
import com.ecommerce.ecommnerce.user.dto.request.UserRegistrationRequestDto;
import com.ecommerce.ecommnerce.user.dto.response.UserResponseDto;
import com.ecommerce.ecommnerce.user.service.UserService;
import com.ecommerce.ecommnerce.common.utils.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")

    public ResponseEntity<CommonResponse> registerUser(@Valid @RequestBody UserRegistrationRequestDto requestDto){


        UserResponseDto userResponseDto = userService.registerUser(requestDto);
        ResponseEntity<CommonResponse> success = ResponseUtil.success(userResponseDto);

        return success;



//        try {
//            UserResponseDto userResponseDto = userService.registerUser(requestDto);
//
//           return ResponseUtil.success(userResponseDto);
//        }catch (Exception e){
//            return  ResponseUtil.failed("Unable to Process");
//        }
    }

    @PostMapping("/address/add")

    public ResponseEntity<CommonResponse> addAddress(@Valid @RequestBody AddressAddRequestDto requestDto) {


        try {
            String response = userService.addAddress(requestDto);
            return ResponseUtil.success(response);
        }catch (Exception e){
            return  ResponseUtil.failed("Unable to Process");
        }

    }
}
