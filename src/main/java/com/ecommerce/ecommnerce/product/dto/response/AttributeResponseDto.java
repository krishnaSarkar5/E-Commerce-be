package com.ecommerce.ecommnerce.product.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttributeResponseDto {


    private String attributeName;

    private String attributeValue;


}
