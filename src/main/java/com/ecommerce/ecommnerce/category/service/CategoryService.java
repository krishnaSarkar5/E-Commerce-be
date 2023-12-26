package com.ecommerce.ecommnerce.category.service;

import com.ecommerce.ecommnerce.category.dto.request.CategoryAddRequestDto;
import com.ecommerce.ecommnerce.category.dto.response.CategoryGetAllResponseDto;
import com.ecommerce.ecommnerce.category.dto.response.CategoryResponseDto;
import com.ecommerce.ecommnerce.common.dto.request.AllDataGetRequestDto;
import com.ecommerce.ecommnerce.common.dto.request.IdDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto createCategory(CategoryAddRequestDto categoryAddRequestDto);

    CategoryGetAllResponseDto getAllCategory(AllDataGetRequestDto allDataGetRequestDto);

    CategoryGetAllResponseDto getAllCategoryTree();

    String deleteCategoryById(IdDto idDto);


}
