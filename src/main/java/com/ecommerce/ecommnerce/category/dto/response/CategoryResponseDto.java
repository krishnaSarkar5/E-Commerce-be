package com.ecommerce.ecommnerce.category.dto.response;

import com.ecommerce.ecommnerce.category.entity.Category;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryResponseDto {

    private Long id;

    private String title;

    private Long parentCategoryId;

    private String parentCategoryTitle;

    private Long rootParentCategoryId;

    private int noOfChildCategories ;

    private List<CategoryAttributeResponseDto> searchFields;

    List<CategoryResponseDto> childCategories;
}
