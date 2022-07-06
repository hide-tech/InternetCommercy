package com.yazykov.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("roles")
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @Id
    private Long id;
    private String name;
}
