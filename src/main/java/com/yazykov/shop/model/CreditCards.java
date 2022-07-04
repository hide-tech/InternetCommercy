package com.yazykov.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("credit_cards")
public class CreditCards {
    @Id
    private Long id;
    private Long customerId;
    private String cardNumber;
    private LocalDate expireDate;
    private String cvv;
}
