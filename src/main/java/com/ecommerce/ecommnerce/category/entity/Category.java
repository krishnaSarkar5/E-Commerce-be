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

    @Column(name = "title" , columnDefinition = " VARCHAR(255) NOT NULL" )
    private String title;

    @Column(name = "parent_category_id" , columnDefinition = " BIGINT" )
    private Long parentCategoryId;

    @Column(name = "parent_category_title" , columnDefinition = " VARCHAR(255)" )
    private String parentCategoryTitle;

    @Column(name = "root_parent_category_id" , columnDefinition = " BIGINT NOT NULL" )
    private Long rootParentCategoryId;

    @Column(name = "status" , columnDefinition = " smallint NOT NULL" )
    private Byte status;

    @Embedded
    private CreateUpdateInformation createUpdateInformation;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonBackReference
    private List<CategoryAttribute> attributes;




}
