package com.ecommerce.ecommnerce.common.dto.request;

import com.ecommerce.ecommnerce.product.dto.request.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AllDataGetRequestDto {

    private int offset = 0;

    private int pageSize = 10;


    private List<SearchCriteria> searchCriteriaList;

    private String orderByField ="id";

    private String orderType = "asc";
}
