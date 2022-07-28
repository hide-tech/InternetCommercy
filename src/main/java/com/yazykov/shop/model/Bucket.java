package com.yazykov.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bucket {
    private Long customerId;
    private Address deliveryAddress;
    private Collection<Product> products;
    private LocalDateTime paid;
}
