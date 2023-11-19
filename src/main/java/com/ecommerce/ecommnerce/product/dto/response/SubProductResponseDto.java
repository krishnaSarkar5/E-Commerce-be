package com.ecommerce.ecommnerce.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubProductResponseDto {

    private Double price;

//    private String image;

    private Double discount;

    private String currency;

    private Long stock;

    private List<AttributeResponseDto> attributeResponseDtoList;

}
