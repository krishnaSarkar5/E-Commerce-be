package com.ecommerce.ecommnerce.auth.controller;



import com.ecommerce.ecommnerce.auth.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AuthenticationService authenticationService;
    @GetMapping("/test")
//    @SecurityRequirement(name = "bearer-key")
    public String test(){

        return "Hello";
    }

//    @GetMapping("/logout")
//    @SecurityRequirement(name = "bearer-key")
//    public void logout(@RequestBody LogoutRequestDto logoutRequestDto){
//        authenticationService.logout(logoutRequestDto);
//    }
}
