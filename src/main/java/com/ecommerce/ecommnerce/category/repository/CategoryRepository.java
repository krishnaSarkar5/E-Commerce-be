package com.ecommerce.ecommnerce.category.repository;

import com.ecommerce.ecommnerce.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByIdAndStatus(Long id, Byte status);

}
