package com.yazykov.shop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {
    private Long zip;
    private String country;
    private String city;
    private String street;
    private String building;
    private String extension;
    private String flat;
}
