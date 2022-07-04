package com.yazykov.shop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreditCardDto {
    private Long customerId;
    private String cardNumber;
    private LocalDate expireDate;
    private String cvv;
}
