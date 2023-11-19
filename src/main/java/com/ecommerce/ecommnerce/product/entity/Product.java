package com.ecommerce.ecommnerce.product.entity;

import com.ecommerce.ecommnerce.category.entity.Category;
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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String title;

    private String description;


    private  String imageUrl;


    private String brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL  /*, orphanRemoval = true*/)
    @JsonBackReference
    private List<SubProduct> subProducts;

    @Embedded
    private CreateUpdateInformation createUpdateInformation;



}
