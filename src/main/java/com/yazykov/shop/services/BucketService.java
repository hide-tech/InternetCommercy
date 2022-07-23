package com.yazykov.shop.services;

import com.yazykov.shop.dto.AddressDto;
import com.yazykov.shop.dto.BucketDto;
import com.yazykov.shop.dto.ProductDto;
import com.yazykov.shop.mappers.AddressMapper;
import com.yazykov.shop.mappers.BucketMapper;
import com.yazykov.shop.mappers.ProductMapper;
import com.yazykov.shop.model.Bucket;
import com.yazykov.shop.model.Product;
import com.yazykov.shop.repositories.BucketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BucketService {

    private final BucketRepository repository;
    private final BucketMapper bucketMapper;
    private final AddressMapper addressMapper;
    private final ProductMapper productMapper;

    public Mono<BucketDto> createNewBucket(Long customerId){
        Bucket bucket = new Bucket();
        bucket.setCustomerId(customerId);
        return repository.save(bucket).map(bucketMapper::bucketToBucketDto);
    }

    public Mono<BucketDto> setDeliveryAddress(AddressDto addressDto, Long customerId){
        return repository.findByCustomerId(customerId)
                .doOnNext(bucket -> bucket.setDeliveryAddress(addressMapper.addressDtoToAddress(addressDto)))
                .flatMap(repository::save)
                .map(bucketMapper::bucketToBucketDto);
    }

    public Mono<BucketDto> addProductInBucket(ProductDto productDto, Long customerId){
        return repository.findByCustomerId(customerId)
                .doOnNext(bucket -> {
                    Collection<Product> products = bucket.getProducts();
                    if (products == null){
                        products = new ArrayList<>();
                    }
                    products.add(productMapper.productDtoToProduct(productDto));
                    bucket.setProducts(products);
                })
                .flatMap(repository::save)
                .map(bucketMapper::bucketToBucketDto);
    }

    public Mono<BucketDto> removeProductFromBucket(ProductDto productDto, Long customerId){
        return repository.findByCustomerId(customerId)
                .doOnNext(bucket -> {
                    List<Product> list = bucket.getProducts().stream()
                            .filter(pr -> !pr.equals(productMapper.productDtoToProduct(productDto)))
                            .collect(Collectors.toList());
                    bucket.setProducts(list);
                })
                .flatMap(repository::save)
                .map(bucketMapper::bucketToBucketDto);
    }

    public Mono<BucketDto> setPaid(Long customerId){
        return repository.findByCustomerId(customerId)
                .doOnNext(bucket -> bucket.setPaid(LocalDateTime.now()))
                .flatMap(repository::save)
                .map(bucketMapper::bucketToBucketDto);
    }

    public Mono<BucketDto> getBucketByCustomerId(Long customerId){
        return repository.findByCustomerId(customerId)
                .map(bucketMapper::bucketToBucketDto);
    }
}
