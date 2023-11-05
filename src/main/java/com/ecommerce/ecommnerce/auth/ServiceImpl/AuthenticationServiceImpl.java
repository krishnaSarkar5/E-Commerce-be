package com.ecommerce.ecommnerce.auth.ServiceImpl;


import com.ecommerce.ecommnerce.auth.dto.request.LoginRequestDto;
import com.ecommerce.ecommnerce.auth.service.AuthenticationService;
import com.ecommerce.ecommnerce.common.response.CommonResponse;
import com.ecommerce.ecommnerce.common.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private LoginService loginService;
    @Override
    public ResponseEntity<CommonResponse> login(LoginRequestDto requestDto) {
        try {
            String data = loginService.login(requestDto);
            return ResponseUtil.success(data);

        }catch (Exception e){
            return ResponseUtil.failed("Unsuccessful");
        }

    }

    @Override
    public ResponseEntity<CommonResponse> logout() {

        try {
            loginService.logout();
            return ResponseUtil.success("Logout Successfully");

        }catch (Exception e){
            return ResponseUtil.failed("Unsuccessful");
        }



    }
}
