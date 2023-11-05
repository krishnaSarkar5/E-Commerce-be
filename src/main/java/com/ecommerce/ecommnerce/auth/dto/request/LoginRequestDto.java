package com.ecommerce.ecommnerce.auth.dto.request;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {

    private String channel;

    @NonNull
    private String email;

    @NonNull
    private String password;


}
