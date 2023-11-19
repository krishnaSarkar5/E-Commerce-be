package com.ecommerce.ecommnerce.product.dto.request;



import jakarta.validation.constraints.NotEmpty;
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

    private List<SubProductRequestDto> subProductList;


}
