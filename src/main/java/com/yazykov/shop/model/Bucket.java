package com.yazykov.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("buckets")
public class Bucket {
    @Id
    private Long id;
    private Customer customer;
    private Address deliveryAddress;
    private Collection<Product> products;
    private LocalDateTime paid;
}
