package com.ecommerce.ecommnerce.auth.controller;



import com.ecommerce.ecommnerce.auth.dto.request.LoginRequestDto;
import com.ecommerce.ecommnerce.common.response.CommonResponse;
import com.ecommerce.ecommnerce.auth.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public ResponseEntity<CommonResponse> login(@RequestBody LoginRequestDto requestDto){
        ResponseEntity<CommonResponse> login = authenticationService.login(requestDto);
        return  login;
    }


    @PostMapping("/logout")
//    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<CommonResponse> logout(){
        return authenticationService.logout();
    }




}
