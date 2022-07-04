package com.yazykov.shop.mappers;

import com.yazykov.shop.dto.CreditCardDto;
import com.yazykov.shop.model.CreditCard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {

    CreditCardDto creditCardToCreditCardDto(CreditCard creditCard);

    CreditCard creditCardDtoToCreditCard(CreditCardDto creditCardDto);

}
