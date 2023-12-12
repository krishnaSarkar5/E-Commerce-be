package com.ecommerce.ecommnerce.user.entity;

import com.ecommerce.ecommnerce.common.model.CreateUpdateInformation;
import com.ecommerce.ecommnerce.user.customannotations.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name" , columnDefinition = " VARCHAR(255) NOT NULL")
    private String firstName;

    @Column(name = "last_name" , columnDefinition = " VARCHAR(255) NOT NULL")
    private String lastName;

    @Column(name = "street_address" , columnDefinition = " VARCHAR(255) NOT NULL")
    private String streetAddress;

    @Column(name = "city" , columnDefinition = " VARCHAR(255) NOT NULL")
    private String city;

    @Column(name = "state" , columnDefinition = " VARCHAR(255) NOT NULL")
    private String state;

    @Column(name = "zip_code" , columnDefinition = " VARCHAR(255) NOT NULL")
    private String zipCod;

    @Column(name = "phone" , columnDefinition = " VARCHAR(255) NOT NULL")
    @Phone
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    @ToString.Exclude
    private User user;

    @Embedded
    private CreateUpdateInformation createUpdateInformation;


}
