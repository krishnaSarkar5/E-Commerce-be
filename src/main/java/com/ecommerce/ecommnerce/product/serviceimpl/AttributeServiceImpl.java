package com.ecommerce.ecommnerce.product.serviceimpl;

import com.ecommerce.ecommnerce.common.dto.request.IdDto;
import com.ecommerce.ecommnerce.common.enums.ExceptionMessage;
import com.ecommerce.ecommnerce.common.enums.Status;
import com.ecommerce.ecommnerce.common.enums.SuccessMessage;
import com.ecommerce.ecommnerce.common.exception.ServiceException;
import com.ecommerce.ecommnerce.product.dto.request.AttributeAddRequestDto;
import com.ecommerce.ecommnerce.product.dto.request.AttributeUpdateRequestDto;
import com.ecommerce.ecommnerce.product.dto.response.AttributeResponseDto;
import com.ecommerce.ecommnerce.product.dtoconverter.AttributeConverter;
import com.ecommerce.ecommnerce.product.entity.Attribute;
import com.ecommerce.ecommnerce.product.entity.SubProduct;
import com.ecommerce.ecommnerce.product.repository.AttributeRepository;
import com.ecommerce.ecommnerce.product.repository.SubProductRepository;
import com.ecommerce.ecommnerce.product.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private SubProductRepository subProductRepository;


    @Override
    @Transactional
    public AttributeResponseDto addAttribute(AttributeAddRequestDto attributeAddRequestDto) {

        SubProduct subProductFromDB = getSubProductFromDB(attributeAddRequestDto.getSubProductId());

        Attribute attribute = AttributeConverter.convertAttributeDtoToEntity(attributeAddRequestDto);

        attribute.setSubProduct(subProductFromDB);

        attribute.setProduct(subProductFromDB.getProduct());

        Attribute savedAttribute = saveAttributeToDB(attribute);

        List<Attribute> attributes = subProductFromDB.getAttributes();

        attributes.add(savedAttribute);

        subProductFromDB.setAttributes(attributes);

        saveSubProductToDB(subProductFromDB);

        AttributeResponseDto attributeResponseDto = AttributeConverter.convertAttributeEntityToResponseDto(savedAttribute);

        return attributeResponseDto;
    }

    @Override
    public AttributeResponseDto updateAttribute(AttributeUpdateRequestDto requestDto) {

        Attribute attributeFromDB = getAttributeFromDB(requestDto.getId());

        Attribute updatedAttribute = AttributeConverter.convertAttributeDtoToEntity(requestDto, attributeFromDB);

        Attribute savedUpdatedAttribute = saveAttributeToDB(updatedAttribute);

        AttributeResponseDto attributeResponseDto = AttributeConverter.convertAttributeEntityToResponseDto(savedUpdatedAttribute);

        return attributeResponseDto;
    }

    @Override
    public String deleteAttribute(IdDto idDto) {

        Attribute attributeFromDB = getAttributeFromDB(idDto.getId());

        attributeFromDB.setStatus(Status.INACTIVE.getValue());

        saveAttributeToDB(attributeFromDB);

        return SuccessMessage.ATTRIBUTE_UPDATED.getValue();
    }


    private Attribute getAttributeFromDB(Long attributeId){
       return attributeRepository.findByIdAndStatus(attributeId, Status.ACTIVE.getValue()).orElseThrow(()->new ServiceException(ExceptionMessage.INVALID_ATTRIBUTE.getMessage()));
    }

    private Attribute saveAttributeToDB(Attribute attribute){
        return attributeRepository.save(attribute);
    }


    private SubProduct getSubProductFromDB(Long subProductId){
        return subProductRepository.findByIdAndStatus(subProductId,Status.ACTIVE.getValue()).orElseThrow(()-> new ServiceException(ExceptionMessage.INVALID_SUB_PRODUCT_ID.getMessage()));
    }

    private SubProduct saveSubProductToDB(SubProduct subProduct){
        return subProductRepository.save(subProduct);
    }
}
