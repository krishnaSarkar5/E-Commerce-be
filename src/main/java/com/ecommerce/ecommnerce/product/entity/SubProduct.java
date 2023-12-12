package com.ecommerce.ecommnerce.product.entity;

import com.ecommerce.ecommnerce.common.model.CreateUpdateInformation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class SubProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Product product;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "subProduct" , cascade = CascadeType.ALL  /*, orphanRemoval = true*/)
    @JsonBackReference
    private List<Attribute> attributes;

    private Double price;

    private Double discount;

    private String currency;

    private Long stock;

    private Byte status;

    private Long productReferenceId;

    @Embedded
    private CreateUpdateInformation createUpdateInformation;
}
