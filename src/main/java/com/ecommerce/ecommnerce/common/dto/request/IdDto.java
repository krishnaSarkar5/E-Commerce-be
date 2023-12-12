package com.ecommerce.ecommnerce.common.dto.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdDto {

    @NotEmpty(message = "Id Required")
    private Long id;

}
