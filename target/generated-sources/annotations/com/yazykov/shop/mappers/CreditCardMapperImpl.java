package com.yazykov.shop.mappers;

import com.yazykov.shop.dto.CreditCardDto;
import com.yazykov.shop.model.CreditCard;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-23T13:16:17+0300",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class CreditCardMapperImpl implements CreditCardMapper {

    @Override
    public CreditCardDto creditCardToCreditCardDto(CreditCard creditCard) {
        if ( creditCard == null ) {
            return null;
        }

        CreditCardDto creditCardDto = new CreditCardDto();

        creditCardDto.setCustomerId( creditCard.getCustomerId() );
        creditCardDto.setCardNumber( creditCard.getCardNumber() );
        creditCardDto.setExpireDate( creditCard.getExpireDate() );
        creditCardDto.setCvv( creditCard.getCvv() );

        return creditCardDto;
    }

    @Override
    public CreditCard creditCardDtoToCreditCard(CreditCardDto creditCardDto) {
        if ( creditCardDto == null ) {
            return null;
        }

        CreditCard creditCard = new CreditCard();

        creditCard.setCustomerId( creditCardDto.getCustomerId() );
        creditCard.setCardNumber( creditCardDto.getCardNumber() );
        creditCard.setExpireDate( creditCardDto.getExpireDate() );
        creditCard.setCvv( creditCardDto.getCvv() );

        return creditCard;
    }
}
