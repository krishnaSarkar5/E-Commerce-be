package com.ecommerce.ecommnerce.common.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import com.ecommerce.ecommnerce.common.response.CommonResponse;
import com.ecommerce.ecommnerce.common.utils.ResponseUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler{


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{

			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});

		ResponseEntity<CommonResponse> failed = ResponseUtil.failed(errors);


		return new ResponseEntity<Object>(failed.getBody(), HttpStatus.BAD_REQUEST);
	}

//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//																  HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//		Map<String, String> errors = new HashMap<>();
//		ex.getBindingResult().getAllErrors().forEach((error) ->{
//
//			String fieldName = ((FieldError) error).getField();
//			String message = error.getDefaultMessage();
//			errors.put(fieldName, message);
//		});
//		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
//	}
}