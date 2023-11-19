package com.ecommerce.ecommnerce.category.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryAttributeRequestDto {

    private String field;

    private String dataType;

}
