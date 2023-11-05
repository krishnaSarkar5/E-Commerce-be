package com.ecommerce.ecommnerce.product.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String value;

    @ManyToOne
    @JoinColumn(name = "sub_product_id")
    @JsonManagedReference
    private SubProduct subProduct;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
