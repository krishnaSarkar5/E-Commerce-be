package com.ecommerce.ecommnerce.product.dto.request;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class SearchCriteria {

    private String key;

    private Object value;


    public SearchCriteria(String searchKey, Object searchValue){
        this.key=searchKey;
        this.value=searchValue;
    }
}
