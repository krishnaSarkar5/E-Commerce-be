package com.ecommerce.ecommnerce.category.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryAddRequestDto {

    @NotEmpty(message = "title must not empty")
    private String title;

    private Long parentCategoryId = -1L;

//    private Long rootParentCategoryId = -1L;

    private List<CategoryAttributeRequestDto> searchFields;


}
