package com.yazykov.shop.mappers;

import com.yazykov.shop.dto.ProductDto;
import com.yazykov.shop.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto);

}
