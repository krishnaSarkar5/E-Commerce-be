package com.ecommerce.ecommnerce.user.repository;

import com.ecommerce.ecommnerce.user.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
