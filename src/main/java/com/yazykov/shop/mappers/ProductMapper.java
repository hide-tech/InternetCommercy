package com.yazykov.shop.mappers;

import com.yazykov.shop.dto.ProductDto;
import com.yazykov.shop.model.Product;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    public abstract ProductDto productToProductDto(Product product);

    public Product productDtoToProduct(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setModel(productDto.getModel());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setSupply_date(LocalDateTime.now());
        return product;
    };

}
