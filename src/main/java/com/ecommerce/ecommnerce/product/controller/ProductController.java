package com.ecommerce.ecommnerce.product.controller;


import com.ecommerce.ecommnerce.common.response.CommonResponse;
import com.ecommerce.ecommnerce.common.utils.ResponseUtil;
import com.ecommerce.ecommnerce.product.dto.request.ProductAddRequestDto;
import com.ecommerce.ecommnerce.product.dto.response.ProductResponseDto;
import com.ecommerce.ecommnerce.product.service.ProductService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/create")
    public ResponseEntity<CommonResponse> createProduct(@RequestBody @Valid ProductAddRequestDto requestDto){
        ProductResponseDto product = productService.createProduct(requestDto);
        return ResponseUtil.success(product);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<CommonResponse> getProductById(@PathVariable("productId") Long productId){
        ProductResponseDto productById = productService.getProductById(productId);
        return ResponseUtil.success(productById);
    }


}
