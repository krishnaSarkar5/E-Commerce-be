package com.ecommerce.ecommnerce.auth.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class TokenMaintainTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "email" , nullable = false)
    public String email;

    @Column(name = "phone" , nullable = false)
    public String phone;

    @Column(name = "token" , nullable = false,unique = true)
    public String token;

    @CreationTimestamp
    public LocalDateTime createdAt;

    @CreationTimestamp
    public LocalDateTime updatedAt;

    public Integer status;


}
