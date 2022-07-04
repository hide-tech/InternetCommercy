package com.yazykov.shop.dto;

import com.yazykov.shop.model.Address;
import com.yazykov.shop.model.Customer;
import com.yazykov.shop.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class BucketDto {
    private CustomerDto customer;
    private AddressDto deliveryAddress;
    private Collection<ProductDto> products;
    private LocalDateTime paid;
}
