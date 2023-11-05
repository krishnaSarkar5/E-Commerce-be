package com.ecommerce.ecommnerce.auth.ServiceImpl;


import com.ecommerce.ecommnerce.auth.dto.UserToken;
import com.ecommerce.ecommnerce.auth.dto.request.LoginRequestDto;
import com.ecommerce.ecommnerce.auth.entity.TokenMaintainTable;
import com.ecommerce.ecommnerce.auth.repository.TokenMaintainRepository;
import com.ecommerce.ecommnerce.user.entity.User;
import com.ecommerce.ecommnerce.common.exception.ServiceException;
import com.ecommerce.ecommnerce.user.repository.UserRepository;
import com.ecommerce.ecommnerce.common.config.security.securitycomponents.JwtUserDetailService;
import com.ecommerce.ecommnerce.common.config.security.securitycomponents.JwtUtils;
import com.ecommerce.ecommnerce.common.utils.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Component
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenMaintainRepository tokenMaintainRepository;

    @Autowired
    private JwtUserDetailService userDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationUtil authenticationUtil;


    @Autowired
    private JwtUtils jwtUtils;

    public String login(LoginRequestDto request){


        UserDetails userDetails = getUserDetails(request.getEmail());


        return getJwtToken(userDetails,request);
    }

    public void logout(){

        UserToken loggedInUser = getLoggedInUser();

        // User user = userRepository.findByEmailAndStatus(request.getEmail(), 1).orElseThrow(() -> new UsernameNotFoundException("User name not found"));
        TokenMaintainTable tokenMaintainTable=tokenMaintainRepository.findByTokenAndStatus(loggedInUser.getToken(),1).orElseThrow(() -> new UsernameNotFoundException("User name not found"));
        tokenMaintainTable.setStatus(0);
        tokenMaintainTable.setUpdatedAt(LocalDateTime.now());
        tokenMaintainRepository.save(tokenMaintainTable);

    }

    private UserDetails getUserDetails(String email){
      return   userDetailService.loadUserByUsername(email);
    }


    private String getJwtToken(UserDetails userDetails , LoginRequestDto request){


        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails,request.getPassword()));
        }catch (DisabledException e){
            throw new ServiceException("User Disabled", HttpStatus.UNAUTHORIZED);
        }catch (BadCredentialsException e){
            throw new ServiceException("Invalid Credential",HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            throw new ServiceException("Invalid Credential",HttpStatus.UNAUTHORIZED);
        }


        Map<String ,Object> claims = new HashMap<>();

        claims.put("channel",request.getChannel());
        claims.put("status",userDetails.isEnabled());


        String token = jwtUtils.generateToken(userDetails,claims);

        User user = userRepository.findByEmailAndStatus(request.getEmail(), (byte)1).orElseThrow(() -> new UsernameNotFoundException("User name not found"));
        TokenMaintainTable tokenMaintainTable=new TokenMaintainTable();
        tokenMaintainTable.setPhone(user.getPhone());
        tokenMaintainTable.setEmail(user.getEmail());
        tokenMaintainTable.setCreatedAt(LocalDateTime.now());
        tokenMaintainTable.setStatus(1);
        tokenMaintainTable.setToken(token);
        //tokenMaintainTable.setUpdatedAt();
        tokenMaintainRepository.save(tokenMaintainTable);


        return token;
    }


    private UserToken getLoggedInUser(){
        return authenticationUtil.currentLoggedInUser();
    }
}
