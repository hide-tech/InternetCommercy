package com.yazykov.shop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private String id;
    private String name;
    private String model;
    private String description;
    private BigDecimal price;
    private Long quantity;
}
