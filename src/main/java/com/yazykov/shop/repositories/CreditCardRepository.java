package com.yazykov.shop.repositories;

import com.yazykov.shop.model.CreditCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends CrudRepository<Long, CreditCard> {

}
