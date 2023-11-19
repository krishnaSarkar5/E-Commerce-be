package com.ecommerce.ecommnerce.product.dto.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "price can not be null")
    private Double price;

//    private String image;
    @NotNull(message = "discount can not be null")
    private Double discount;

    @NotEmpty(message = "currency can't be empty")
    private String currency;

    @NotNull(message = "stock can not be null")
    private Long stock;

    @NotNull(message = "attribute must have one element")
    @Size(min = 1, message = "attribute must contain at least one element")
    private List<@Valid  AttributeRequestDto> attributeRequestDtos;

}
