package com.ecommerce.ecommnerce.product.dto.response;


import com.ecommerce.ecommnerce.product.entity.SubProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

    private Long id;

    private String title;

    private String description;

    private String brand;

    private Long categoryId;

    private List<SubProductResponseDto> subProductResponseDtoList;


}
