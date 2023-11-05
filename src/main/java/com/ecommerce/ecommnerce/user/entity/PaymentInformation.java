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

    @Column(name = "card_holder_name",nullable = false)
    private String cardHolderName;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "expired_at", nullable = false)
    private LocalDate expiredAt;

    @Column(name = "cvv", nullable = false)
    private String cvv;



}
