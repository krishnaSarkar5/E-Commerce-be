package com.ecommerce.ecommnerce.product.controller;

import com.ecommerce.ecommnerce.common.dto.request.IdDto;
import com.ecommerce.ecommnerce.common.response.CommonResponse;
import com.ecommerce.ecommnerce.common.utils.ResponseUtil;
import com.ecommerce.ecommnerce.product.dto.request.AttributeAddRequestDto;
import com.ecommerce.ecommnerce.product.dto.request.AttributeUpdateRequestDto;
import com.ecommerce.ecommnerce.product.dto.response.AttributeResponseDto;
import com.ecommerce.ecommnerce.product.service.AttributeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;


    @PostMapping("/add")
    public ResponseEntity<CommonResponse> addAttribute(@Valid @RequestBody AttributeAddRequestDto attributeAddRequestDto){

        AttributeResponseDto attributeResponseDto = attributeService.addAttribute(attributeAddRequestDto);

        return ResponseUtil.success(attributeResponseDto);

    }

    @PostMapping("/update")
    public ResponseEntity<CommonResponse> updateAttribute(@Valid @RequestBody AttributeUpdateRequestDto attributeUpdateRequestDto){

        AttributeResponseDto attributeResponseDto = attributeService.updateAttribute(attributeUpdateRequestDto);

        return ResponseUtil.success(attributeResponseDto);

    }

    @PostMapping("/delete")
    public ResponseEntity<CommonResponse> deleteAttribute(@Valid @RequestBody IdDto idDto){

        String response = attributeService.deleteAttribute(idDto);

        return ResponseUtil.success(response);

    }


}
