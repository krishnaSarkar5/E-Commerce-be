package com.ecommerce.ecommnerce.product.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttributeRequestDto {


    private String attributeName;

    private String attributeValue;


}
