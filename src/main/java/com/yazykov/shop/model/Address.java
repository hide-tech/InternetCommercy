package com.yazykov.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private Long zip;
    private String country;
    private String city;
    private String street;
    private String building;
    private String extension;
    private String flat;
}
