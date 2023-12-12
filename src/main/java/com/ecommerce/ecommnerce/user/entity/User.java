package com.ecommerce.ecommnerce.user.entity;


import com.ecommerce.ecommnerce.common.filedencryption.AttributeEncryptor;
import com.ecommerce.ecommnerce.common.model.CreateUpdateInformation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name" , columnDefinition = " VARCHAR(255) NOT NULL")
    private String firstName;

    @Column(name = "last_name" , columnDefinition = " VARCHAR(255) NOT NULL")
    private String lastName;

    // encrypted field
    @Column(name = "email" , columnDefinition = " VARCHAR(255) NOT NULL UNIQUE")
    private String email;


    @Column(name = "phone" ,  columnDefinition = " VARCHAR(255) NOT NULL UNIQUE")
    private String phone;


    @Column(name = "password" ,  columnDefinition = " VARCHAR(255) NOT NULL ")
    @Convert(converter = AttributeEncryptor.class)
    private  String password;


    @Column(name = "dob")
    private LocalDate dob;

    @Embedded
    private CreateUpdateInformation createUpdateInformation;

    private Byte status;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL  , orphanRemoval = true)
    @JsonBackReference
    private List<Address> addresses = new ArrayList<>();;

    @Embedded
    @ElementCollection
    @CollectionTable(name = "payment_information" , joinColumns = @JoinColumn(name = "user_id"))
    private List<PaymentInformation> paymentInformation = new ArrayList<>();

//    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Rating> ratings = new ArrayList<>();
//
//
//    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
//    private List<Review> reviews = new ArrayList<>();

}
