package com.yazykov.shop.services;

import com.yazykov.shop.dto.CreditCardDto;
import com.yazykov.shop.mappers.CreditCardMapper;
import com.yazykov.shop.repositories.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository repository;
    private final CreditCardMapper mapper;

    public Mono<CreditCardDto> saveCreditCard(CreditCardDto creditCardDto){
        return repository.save(mapper.creditCardDtoToCreditCard(creditCardDto))
                .map(mapper::creditCardToCreditCardDto);
    }

    public Flux<CreditCardDto> getAllCreditCards(Long customerId){
        return repository.findAllByCustomerId(customerId)
                .map(mapper::creditCardToCreditCardDto);
    }

    public void deleteCreditCard(Long cardId){
        repository.deleteById(cardId);
    }
}
