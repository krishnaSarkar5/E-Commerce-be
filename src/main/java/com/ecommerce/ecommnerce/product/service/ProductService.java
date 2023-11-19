package com.ecommerce.ecommnerce.product.service;

import com.ecommerce.ecommnerce.product.dto.request.ProductAddRequestDto;
import com.ecommerce.ecommnerce.product.dto.response.ProductResponseDto;

public interface ProductService {

    public ProductResponseDto createProduct(ProductAddRequestDto requestDto);

}
