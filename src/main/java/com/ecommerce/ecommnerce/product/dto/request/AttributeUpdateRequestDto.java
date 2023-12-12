package com.ecommerce.ecommnerce.product.dto.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttributeUpdateRequestDto {

    @NotEmpty(message = "Id required")
    private Long id;

    @NotEmpty(message = "attribute name can't be empty")
    private String attributeName;

    @NotEmpty(message = "attribute value can't be empty")
    private String attributeValue;


}
