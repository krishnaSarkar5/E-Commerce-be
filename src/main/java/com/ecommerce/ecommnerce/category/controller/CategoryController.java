package com.ecommerce.ecommnerce.category.controller;


import com.ecommerce.ecommnerce.category.dto.request.CategoryAddRequestDto;
import com.ecommerce.ecommnerce.category.dto.response.CategoryResponseDto;
import com.ecommerce.ecommnerce.category.service.CategoryService;
import com.ecommerce.ecommnerce.common.response.CommonResponse;
import com.ecommerce.ecommnerce.common.utils.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create-category")
    public ResponseEntity<CommonResponse> createCategory(@Valid @RequestBody CategoryAddRequestDto categoryAddRequestDto){

        try {
            CategoryResponseDto category = categoryService.createCategory(categoryAddRequestDto);
            return ResponseUtil.success(category);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.failed("Something wrong");
        }

    }


}
