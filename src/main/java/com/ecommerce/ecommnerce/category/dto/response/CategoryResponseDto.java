package com.ecommerce.ecommnerce.category.dto.response;

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

    private Long rootParentCategoryId;

    private List<CategorySearchFieldResponseDto> searchFields;
}
