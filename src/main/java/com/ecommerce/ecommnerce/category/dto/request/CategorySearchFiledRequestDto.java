package com.ecommerce.ecommnerce.category.dto.request;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategorySearchFiledRequestDto {

    private String field;

    private String dataType;

}
