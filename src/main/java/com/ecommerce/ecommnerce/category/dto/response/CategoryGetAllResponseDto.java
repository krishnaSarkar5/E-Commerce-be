package com.ecommerce.ecommnerce.category.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryGetAllResponseDto {

    Map<String, Object>  metaData;

    List<CategoryResponseDto> categoryResponseDtoList;

}
