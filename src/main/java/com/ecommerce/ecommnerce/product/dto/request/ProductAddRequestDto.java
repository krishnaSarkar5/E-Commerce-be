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
public class ProductAddRequestDto {

    @NotEmpty(message = "title can not be empty")
    private String title;

    @NotEmpty(message = "description can not be empty")
    private String description;

    @NotEmpty(message = "brand can not be empty")
    private String brand;

//    @NotEmpty(message = "categoryId can not be empty")
    private Long categoryId;

    @NotNull(message = "sub product must have one element")
    @Size(min = 1, message = "sub product must contain at least one element")
    private List<@Valid  SubProductRequestDto> subProductList;


}
