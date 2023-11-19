package com.ecommerce.ecommnerce.category.dto.response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryAttributeResponseDto {

    private Long id;

    private String field;

    private String dataType;
}
