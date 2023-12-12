package com.ecommerce.ecommnerce.product.service;

import com.ecommerce.ecommnerce.common.dto.request.IdDto;
import com.ecommerce.ecommnerce.product.dto.request.AttributeAddRequestDto;
import com.ecommerce.ecommnerce.product.dto.request.AttributeUpdateRequestDto;
import com.ecommerce.ecommnerce.product.dto.response.AttributeResponseDto;

public interface AttributeService {


    public AttributeResponseDto addAttribute(AttributeAddRequestDto attributeAddRequestDto);

    public AttributeResponseDto updateAttribute(AttributeUpdateRequestDto requestDto);

    public String deleteAttribute(IdDto idDto);

}
