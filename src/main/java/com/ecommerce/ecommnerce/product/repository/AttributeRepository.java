package com.ecommerce.ecommnerce.product.repository;

import com.ecommerce.ecommnerce.product.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttributeRepository extends JpaRepository<Attribute,Long> {

    Optional<Attribute> findByIdAndStatus(Long id,Byte status);



}
