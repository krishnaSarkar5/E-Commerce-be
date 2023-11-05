package com.ecommerce.ecommnerce.category.dtoconverter;

import com.ecommerce.ecommnerce.category.dto.request.CategoryAddRequestDto;
import com.ecommerce.ecommnerce.category.dto.request.CategorySearchFiledRequestDto;
import com.ecommerce.ecommnerce.category.dto.response.CategoryResponseDto;
import com.ecommerce.ecommnerce.category.dto.response.CategorySearchFieldResponseDto;
import com.ecommerce.ecommnerce.category.entity.Category;
import com.ecommerce.ecommnerce.category.entity.CategorySearchField;
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

        List<CategorySearchField> searchFields = category.getSearchFields();

        List<CategorySearchFieldResponseDto> searchFieldsDtoList = new ArrayList<>();

        if(!Objects.isNull(searchFields) && searchFields.size()>0){

            for(CategorySearchField searchField : searchFields){
                CategorySearchFieldResponseDto categorySearchFieldResponseDto = convertCategorySearchFieldEntityToResponseDto(searchField);
                searchFieldsDtoList.add(categorySearchFieldResponseDto);
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


    public static CategorySearchFieldResponseDto convertCategorySearchFieldEntityToResponseDto(CategorySearchField categorySearchField){
        return CategorySearchFieldResponseDto
                .builder()
                .id(categorySearchField.getId())
                .field(categorySearchField.getField())
                .dataType(categorySearchField.getDataType())
                .build();
    }

    public static CategorySearchField convertCategorySearchFieldRequestDtoToEntity(CategorySearchFiledRequestDto requestDto){
        return  CategorySearchField
                .builder()
                .field(requestDto.getField())
                .dataType(requestDto.getDataType())
                .status(Status.ACTIVE.getValue())
                .build();
    }
}
