package com.ecommerce.ecommnerce.category.repository;

import com.ecommerce.ecommnerce.category.entity.CategoryAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryAttributeRepository extends JpaRepository<CategoryAttribute,Long> {

    List<CategoryAttribute> findByStatusAndCategoryIdAndCategoryStatus(Byte attributeStatus,Long categoryId,Byte categoryStatus);

}
