package com.ecommerce.ecommnerce.user.serviceimpl;

import com.ecommerce.ecommnerce.common.enums.SuccessMessage;
import com.ecommerce.ecommnerce.common.utils.AuthenticationUtil;
import com.ecommerce.ecommnerce.user.dto.request.AddressAddRequestDto;
import com.ecommerce.ecommnerce.user.dto.request.UserRegistrationRequestDto;
import com.ecommerce.ecommnerce.user.dto.response.UserResponseDto;
import com.ecommerce.ecommnerce.user.dtoconverter.AddressDtoConverter;
import com.ecommerce.ecommnerce.user.dtoconverter.UserDtoConverter;
import com.ecommerce.ecommnerce.user.entity.Address;
import com.ecommerce.ecommnerce.user.entity.User;
import com.ecommerce.ecommnerce.user.repository.AddressRepository;
import com.ecommerce.ecommnerce.user.repository.UserRepository;
import com.ecommerce.ecommnerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AuthenticationUtil authenticationUtil;

    @Override

    public UserResponseDto registerUser(UserRegistrationRequestDto requestDto) {

        User user = UserDtoConverter.convertRegistrationPostRequestToEntity(requestDto);
        User savedUser = userRepository.save(user);

        UserResponseDto userResponseDto = UserDtoConverter.convertUserEntityToUserResponseDto(savedUser);

        return userResponseDto;
    }

    @Override
    @Transactional
    public String addAddress(AddressAddRequestDto requestDto) {

        Long currentLoggedInUserId = getCurrentLoggedInUserId();

        User user = userRepository.findById(currentLoggedInUserId).get();

        Address address = AddressDtoConverter.convertAddressAddRequestDtoToEntity(requestDto);

        address.setUser(user);
//
//        Address savedAddress = addressRepository.save(address);

        List<Address> addresses = user.getAddresses();

        if(Objects.isNull(addresses)){
            addresses = new ArrayList<>();
        }
        addresses.add(address);

        user.setAddresses(addresses);

        userRepository.save(user);

        return SuccessMessage.ADDRESS_ADD.getValue();
    }



    private Long getCurrentLoggedInUserId(){
        return authenticationUtil.currentLoggedInUser().getId();
    }
}
