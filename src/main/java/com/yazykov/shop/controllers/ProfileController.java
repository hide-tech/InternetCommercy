package com.yazykov.shop.controllers;

import com.yazykov.shop.dto.AddressDto;
import com.yazykov.shop.dto.BucketDto;
import com.yazykov.shop.dto.CreditCardDto;
import com.yazykov.shop.dto.ProductDto;
import com.yazykov.shop.services.BucketService;
import com.yazykov.shop.services.CreditCardService;
import com.yazykov.shop.services.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final BucketService bucketService;
    private final PayService payService;
    private final CreditCardService cardService;

    @GetMapping("/{id}")
    public Mono<BucketDto> getBucket(@PathVariable("id") Long customerId){
        return bucketService.getBucketByCustomerId(customerId);
    }

    @PostMapping("/{id}/create")
    public Mono<BucketDto> createBucket(@PathVariable("id") Long customerId){
        return bucketService.createNewBucket(customerId);
    }

    @PostMapping("/{id}/address")
    public Mono<BucketDto> setDeliveryAddress(@RequestBody Mono<AddressDto> addressDto,
                                              @PathVariable("id") Long customerId){
        return addressDto.flatMap(address -> {
            return bucketService.setDeliveryAddress(address,customerId);
        });
    }

    @PostMapping("/{id}/add")
    public Mono<BucketDto> addProduct(@RequestBody Mono<ProductDto> productDto,
                                      @PathVariable("id") Long customerId){
        return productDto.flatMap(product -> {
            return bucketService.addProductInBucket(product, customerId);
        });
    }

    @PostMapping("/{id}/remove")
    public Mono<BucketDto> removeProduct(@RequestBody Mono<ProductDto> productDto,
                                      @PathVariable("id") Long customerId){
        return productDto.flatMap(product -> {
            return bucketService.removeProductFromBucket(product, customerId);
        });
    }

    @PostMapping("/{id}/pay")
    public Mono<BucketDto> payCurrentBucket(@RequestBody Mono<CreditCardDto> creditCard,
                                            @PathVariable("id") Long customerId){
        return creditCard.flatMap(credit -> {
            Boolean payResult = payService.proceed(credit);
            if (payResult){
                return bucketService.setPaid(customerId);
            } else {
                return bucketService.getBucketByCustomerId(customerId);
            }
        });
    }

    @PostMapping("/{id}/credit/add")
    public Mono<CreditCardDto> addNewCard(@RequestBody Mono<CreditCardDto> creditCardDto,
                                          @PathVariable("id") Long customerId){
        return creditCardDto.map(card -> {
            card.setCustomerId(customerId);
            return card;
        }).flatMap(cardService::saveCreditCard);
    }

    @GetMapping("/{id}/credit/all")
    public Flux<CreditCardDto> getAllCards(@PathVariable("id") Long customerId){
        return cardService.getAllCreditCards(customerId);
    }

    @DeleteMapping("/{id}/delete/{card}")
    public void deleteCard(@PathVariable("card") Long cardId){
        cardService.deleteCreditCard(cardId);
    }
}
