package com.yazykov.shop.controllers;

import com.yazykov.shop.dto.ProductDto;
import com.yazykov.shop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final ProductService service;

    @GetMapping
    @PreAuthorize("hasRole('Role_ADMIN') or hasRole('Role_STORE')")
    public Flux<ProductDto> getAllProducts(){
        return service.getAllProducts();
    }

    @PostMapping
    @PreAuthorize("hasRole('Role_ADMIN') or hasRole('Role_STORE')")
    public Mono<ProductDto> addProduct(@RequestBody Mono<ProductDto> productDto){
        return service.insertProduct(productDto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('Role_ADMIN') or hasRole('Role_STORE')")
    public Mono<ProductDto> patchProduct(@PathVariable("id") String id, @RequestBody Mono<ProductDto> productDto){
        return service.updateProduct(id, productDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Role_ADMIN') or hasRole('Role_STORE')")
    public Mono<ProductDto> getProductById(@PathVariable("id") String id){
        return service.getProductById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Role_ADMIN') or hasRole('Role_STORE')")
    public Mono<Void> deleteProductById(@PathVariable("id") String id){
        return service.deleteProduct(id);
    }

    @GetMapping("/price")
    @PreAuthorize("hasRole('Role_ADMIN') or hasRole('Role_STORE')")
    public Flux<ProductDto> getProductInPriceRange(@RequestParam("min") double min,
                                                   @RequestParam("max") double max){
        return service.getAllInRange(min, max);
    }

    @GetMapping("/check")
    public Mono<Boolean> checkProductOnStore(@RequestParam("id") String id, @RequestParam("qty") Long qty){
        return service.checkAvailableProduct(id, qty);
    }

    @PostMapping("/reserve")
    public Mono<Boolean> reserveProduct(@RequestParam("id") String id,
                                            @RequestParam("qty") Long qty){
        return service.reserveProduct(id, qty);
    }
}
