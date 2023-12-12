package com.ecommerce.ecommnerce.user.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentInformation {

    @Column(name = "card_holder_name", columnDefinition = " VARCHAR(255) NOT NULL")
    private String cardHolderName;

    @Column(name = "card_number", columnDefinition = " VARCHAR(255) NOT NULL")
    private String cardNumber;

    @Column(name = "expired_at", columnDefinition = " VARCHAR(255) NOT NULL")
    private LocalDate expiredAt;

    @Column(name = "cvv", columnDefinition = " VARCHAR(255) NOT NULL")
    private String cvv;



}
