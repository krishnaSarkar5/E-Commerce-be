package com.ecommerce.ecommnerce.product.repository;

import com.ecommerce.ecommnerce.product.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute,Long> {
}
