package com.ecommerce.ecommnerce.product.dtoconverter;

import com.ecommerce.ecommnerce.common.enums.ExceptionMessage;
import com.ecommerce.ecommnerce.common.enums.Status;
import com.ecommerce.ecommnerce.common.exception.ServiceException;
import com.ecommerce.ecommnerce.product.dto.request.AttributeAddRequestDto;
import com.ecommerce.ecommnerce.product.dto.request.AttributeUpdateRequestDto;
import com.ecommerce.ecommnerce.product.dto.response.AttributeResponseDto;
import com.ecommerce.ecommnerce.product.entity.Attribute;
import com.ecommerce.ecommnerce.product.enums.AttributeEnum;

public class AttributeConverter {



    public static Attribute convertAttributeDtoToEntity(AttributeAddRequestDto attributeAddRequestDto){

        try {
            AttributeEnum attributeEnum = AttributeEnum.valueOf(attributeAddRequestDto.getAttributeName().trim().toUpperCase());

           return Attribute
                   .builder()
                   .name(attributeEnum.getValue())
                   .value(attributeAddRequestDto.getAttributeValue())
                   .status(Status.ACTIVE.getValue())
                   .build();

        }catch (Exception e){
            throw  new ServiceException(ExceptionMessage.INVALID_ATTRIBUTE.getMessage());
        }

    }



    public static Attribute convertAttributeDtoToEntity(AttributeUpdateRequestDto attributeUpdateRequestDto,Attribute attribute){

        try {
            AttributeEnum attributeEnum = AttributeEnum.valueOf(attributeUpdateRequestDto.getAttributeName().trim().toUpperCase());

            attribute.setName(attribute.getName());
            attribute.setValue(attributeEnum.getValue());

            return attribute;

        }catch (Exception e){
            throw  new ServiceException(ExceptionMessage.INVALID_ATTRIBUTE.getMessage());
        }

    }


    public  static AttributeResponseDto convertAttributeEntityToResponseDto(Attribute attribute){

        return AttributeResponseDto
                .builder()
                .attributeName(attribute.getName())
                .attributeValue(attribute.getValue())
                .build();
    }
}
