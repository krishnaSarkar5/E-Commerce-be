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

    @Column(name = "first_name" , nullable = false)
    private String firstName;

    @Column(name = "last_name" , nullable = false)
    private String lastName;

    @Column(name = "street_address" , nullable = false)
    private String streetAddress;

    @Column(name = "city" , nullable = false)
    private String city;

    @Column(name = "state" , nullable = false)
    private String state;

    @Column(name = "zip_code" , nullable = false)
    private String zipCod;

    @Column(name = "phone" , nullable = false)
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
