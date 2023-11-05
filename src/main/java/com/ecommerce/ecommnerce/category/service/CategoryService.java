package com.ecommerce.ecommnerce.category.service;

import com.ecommerce.ecommnerce.category.dto.request.CategoryAddRequestDto;
import com.ecommerce.ecommnerce.category.dto.response.CategoryResponseDto;

public interface CategoryService {

    CategoryResponseDto createCategory(CategoryAddRequestDto categoryAddRequestDto);

}
