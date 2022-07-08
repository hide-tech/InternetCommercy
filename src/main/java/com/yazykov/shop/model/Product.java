package com.yazykov.shop.model;

import com.yazykov.shop.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String model;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private LocalDateTime supply_date;

    public Product(ProductDto productDto){
        this.name = productDto.getName();
        this.model = productDto.getModel();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.quantity = productDto.getQuantity();
    }
}
