package com.ecommerce.ecommnerce.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubProductRequestDto {


    private Double price;

//    private String image;

    private Double discount;

    private String currency;

    private List<AttributeRequestDto> attributeRequestDtos;

}
