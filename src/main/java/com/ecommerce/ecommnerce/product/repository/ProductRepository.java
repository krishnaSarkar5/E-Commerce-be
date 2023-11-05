package com.ecommerce.ecommnerce.product.repository;

import com.ecommerce.ecommnerce.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
