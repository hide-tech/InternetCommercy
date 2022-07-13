package com.yazykov.shop.services;

import com.yazykov.shop.dto.ProductDto;
import com.yazykov.shop.mappers.ProductMapper;
import com.yazykov.shop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Flux<ProductDto> getAllProducts(){
        return repository.findAll()
                .map(mapper::productToProductDto);
    }

    public Flux<ProductDto> getAllInRange(double min, double max){
        return repository.findByPriceBetween(Range.closed(min,max))
                .map(mapper::productToProductDto);
    }

    public Mono<ProductDto> getProductById(String id){
        return repository.findById(id)
                .map(mapper::productToProductDto);
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> productDto){
        return productDto.map(mapper::productDtoToProduct)
                .flatMap(repository::insert)
                .map(mapper::productToProductDto);
    }

    public Mono<Void> deleteProduct(String id){
        return repository.deleteById(id);
    }

    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDto){
        return repository.findById(id)
                .flatMap(p -> productDto.map(mapper::productDtoToProduct))
                .doOnNext(e -> e.setId(id))
                .flatMap(repository::save)
                .map(mapper::productToProductDto);
    }

    public Mono<Boolean> checkAvailableProduct(String id, Long qty){
        return repository.findById(id)
                .filter(product -> product.getQuantity()>=qty)
                .hasElement();
    }

    public Mono<Boolean> reserveProduct(String id, Long qty){
        return repository.findById(id)
                .filter(p -> p.getQuantity()>=qty)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Not enough in stock")))
                .doOnNext(p -> p.setQuantity(p.getQuantity()-qty))
                .flatMap(repository::save)
                .flatMap(p -> p!=null? Mono.just(true): Mono.just(false));
    }

}
