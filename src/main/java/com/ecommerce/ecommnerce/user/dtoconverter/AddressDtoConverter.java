package com.ecommerce.ecommnerce.user.dtoconverter;

import com.ecommerce.ecommnerce.user.dto.request.AddressAddRequestDto;
import com.ecommerce.ecommnerce.user.entity.Address;

public class AddressDtoConverter {

    public static Address convertAddressAddRequestDtoToEntity(AddressAddRequestDto requestDto){
        return Address.builder()
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .streetAddress(requestDto.getStreetAddress())
                .phone(requestDto.getPhone())
                .city(requestDto.getCity())
                .state(requestDto.getState())
                .zipCod(requestDto.getZipCod())
                .build();
    }
}
