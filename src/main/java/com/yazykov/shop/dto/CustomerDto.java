package com.yazykov.shop.dto;

import com.yazykov.shop.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {
    private String username;
    private String email;
    private String name;
    private String surname;
    private Role role;
    private Collection<CreditCardDto> cards;
}
