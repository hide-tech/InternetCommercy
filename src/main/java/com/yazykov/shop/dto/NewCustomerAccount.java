package com.yazykov.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewCustomerAccount {
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
}
