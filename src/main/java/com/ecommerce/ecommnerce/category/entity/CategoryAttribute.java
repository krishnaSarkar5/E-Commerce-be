package com.ecommerce.ecommnerce.category.entity;

import com.ecommerce.ecommnerce.common.model.CreateUpdateInformation;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class CategoryAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "field" ,columnDefinition = " VARCHAR(255) NOT NULL" )
    private String field;

    @Column(name = "data_type" , columnDefinition = " VARCHAR(255) NOT NULL" )
    private String dataType;

    @Column(name = "status" , columnDefinition = " smallint NOT NULL" )
    private Byte status;

    @Embedded
    private CreateUpdateInformation createUpdateInformation;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private Category category;
}
