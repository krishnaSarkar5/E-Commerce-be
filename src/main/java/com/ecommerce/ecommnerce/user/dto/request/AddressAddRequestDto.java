package com.ecommerce.ecommnerce.user.dto.request;



import com.ecommerce.ecommnerce.user.customannotations.Phone;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressAddRequestDto {

    @NotEmpty(message = "first name can not be empty")
    private String firstName;

    @NotEmpty(message = "last name can not be empty")
    private String lastName;

    @NotEmpty(message = "street address can not be empty")
    private String streetAddress;

    @NotEmpty(message = "city can not be empty")
    private String city;

    @NotEmpty(message = "state can not be empty")
    private String state;

    @NotEmpty(message = "zip code can not be empty")
    private String zipCod;

    @NotEmpty(message = "phone can not be empty")
    @Phone
    private String phone;



}
