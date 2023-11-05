package com.ecommerce.ecommnerce.common.exceptionHandler;


import com.ecommerce.ecommnerce.common.exception.ServiceException;
import com.ecommerce.ecommnerce.common.response.CommonResponse;
import com.ecommerce.ecommnerce.common.utils.ResponseUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

//@RestControllerAdvice
public class AppExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CommonResponse> handleServiceException(Exception ex){

        Object erroMessageDescription = ex.getMessage();

//        if(!Objects.isNull(ex.getIndexError())){
//            erroMessageDescription=(ex.getIndexError());
//
//
//        }

        ResponseEntity<CommonResponse> failed = ResponseUtil.failed(erroMessageDescription);

//        return new ResponseEntity<>(erroMessageDescription,new HttpHeaders(),ex.getStatusCode());

        return failed;
    }

        @ExceptionHandler(value = ServiceException.class)
        public ResponseEntity<Object> handleServiceException(ServiceException ex){

            Object erroMessageDescription = ex.getMessage();

            if(!Objects.isNull(ex.getIndexError())){
                erroMessageDescription=(ex.getIndexError());
            }

            return new ResponseEntity<>(erroMessageDescription,new HttpHeaders(),ex.getStatusCode());

        }



}
