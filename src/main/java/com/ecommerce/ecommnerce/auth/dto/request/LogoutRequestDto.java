package com.ecommerce.ecommnerce.auth.dto.request;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogoutRequestDto {

    @NonNull
    private String email;


}
