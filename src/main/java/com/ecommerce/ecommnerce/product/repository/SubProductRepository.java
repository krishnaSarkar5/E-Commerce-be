package com.ecommerce.ecommnerce.product.repository;


import com.ecommerce.ecommnerce.product.entity.SubProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubProductRepository extends JpaRepository<SubProduct,Long> {

    Optional<SubProduct> findByIdAndStatus(Long id, Byte status);

}
