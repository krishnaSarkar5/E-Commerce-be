package com.ecommerce.ecommnerce.category.repository;

import com.ecommerce.ecommnerce.category.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByIdAndStatus(Long id, Byte status);


    List<Category> findAll(Specification<Category> specification, Pageable pageable);

    List<Category> findAll(Specification<Category> specification);

    List<Category> findAllByStatus(Byte status, Pageable pageable);

    List<Category> findAllByStatus(Byte status);

    List<Category> findAllByParentCategoryIdInAndStatus(List<Long> categoryIdList,Byte status);

}
