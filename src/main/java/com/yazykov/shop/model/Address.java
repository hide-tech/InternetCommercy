package com.yazykov.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("address")
public class Address {
    @Id
    private Long id;
    private Long zip;
    private String country;
    private String city;
    private String street;
    private String building;
    private String extension;
    private String flat;
}
