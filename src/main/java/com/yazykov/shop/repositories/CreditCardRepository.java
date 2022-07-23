package com.yazykov.shop.repositories;

import com.yazykov.shop.model.CreditCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CreditCardRepository extends ReactiveMongoRepository<CreditCard, Long> {

    Flux<CreditCard> findAllByCustomerId(Long customerId);

}
