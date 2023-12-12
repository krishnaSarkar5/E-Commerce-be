package com.ecommerce.ecommnerce.product.service;

import com.ecommerce.ecommnerce.product.dto.request.ProductAddRequestDto;
import com.ecommerce.ecommnerce.product.dto.request.SubProductRequestDto;
import com.ecommerce.ecommnerce.product.dto.response.ProductResponseDto;
import com.ecommerce.ecommnerce.product.dto.response.SubProductResponseDto;

public interface ProductService {

    public ProductResponseDto createProduct(ProductAddRequestDto requestDto);

    public ProductResponseDto getProductById(Long productId);


}
