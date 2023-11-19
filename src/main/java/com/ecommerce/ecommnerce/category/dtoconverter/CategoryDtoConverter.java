package com.ecommerce.ecommnerce.category.dtoconverter;

import com.ecommerce.ecommnerce.category.dto.request.CategoryAddRequestDto;
import com.ecommerce.ecommnerce.category.dto.request.CategoryAttributeRequestDto;
import com.ecommerce.ecommnerce.category.dto.response.CategoryResponseDto;
import com.ecommerce.ecommnerce.category.dto.response.CategoryAttributeResponseDto;
import com.ecommerce.ecommnerce.category.entity.Category;
import com.ecommerce.ecommnerce.category.entity.CategoryAttribute;
import com.ecommerce.ecommnerce.common.enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryDtoConverter {

    public static Category convertCategoryAddRequestDtoToEntity(CategoryAddRequestDto categoryAddRequestDto){
        return Category.builder()
                .title(categoryAddRequestDto.getTitle())
                .parentCategoryId(categoryAddRequestDto.getParentCategoryId())
                .rootParentCategoryId(-1L)
                .status(Status.ACTIVE.getValue())
                .build();
    }


    public static CategoryResponseDto convertCategoryEntityToResponseDto(Category category){

        List<CategoryAttribute> searchFields = category.getAttributes();

        List<CategoryAttributeResponseDto> searchFieldsDtoList = new ArrayList<>();

        if(!Objects.isNull(searchFields) && searchFields.size()>0){

            for(CategoryAttribute searchField : searchFields){
                CategoryAttributeResponseDto categoryAttributeResponseDto = convertCategorySearchFieldEntityToResponseDto(searchField);
                searchFieldsDtoList.add(categoryAttributeResponseDto);
            }


        }

        return CategoryResponseDto
                .builder()
                .id(category.getId())
                .title(category.getTitle())
                .parentCategoryId(category.getParentCategoryId())
                .rootParentCategoryId(category.getRootParentCategoryId())
                .searchFields(searchFieldsDtoList)
                .build();
    }


    public static CategoryAttributeResponseDto convertCategorySearchFieldEntityToResponseDto(CategoryAttribute categoryAttribute){
        return CategoryAttributeResponseDto
                .builder()
                .id(categoryAttribute.getId())
                .field(categoryAttribute.getField())
                .dataType(categoryAttribute.getDataType())
                .build();
    }

    public static CategoryAttribute convertCategorySearchFieldRequestDtoToEntity(CategoryAttributeRequestDto requestDto){
        return  CategoryAttribute
                .builder()
                .field(requestDto.getField())
                .dataType(requestDto.getDataType())
                .status(Status.ACTIVE.getValue())
                .build();
    }
}
