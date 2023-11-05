package com.ecommerce.ecommnerce.category.entity;


import com.ecommerce.ecommnerce.common.model.CreateUpdateInformation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title" , nullable = false )
    private String title;

    @Column(name = "parent_category_id" , nullable = false )
    private Long parentCategoryId;

    @Column(name = "root_parent_category_id" , nullable = false )
    private Long rootParentCategoryId;

    @Column(name = "status" , nullable = false )
    private Byte status;

    @Embedded
    private CreateUpdateInformation createUpdateInformation;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonBackReference
    private List<CategorySearchField> searchFields;




}
