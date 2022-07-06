package com.yazykov.shop.mappers;

import com.yazykov.shop.dto.CreditCardDto;
import com.yazykov.shop.dto.CustomerDto;
import com.yazykov.shop.model.CreditCard;
import com.yazykov.shop.model.Customer;
import com.yazykov.shop.model.Role;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-06T12:40:14+0300",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto customerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setUsername( customer.getUsername() );
        customerDto.setEmail( customer.getEmail() );
        customerDto.setName( customer.getName() );
        customerDto.setSurname( customer.getSurname() );
        Collection<Role> collection = customer.getRoles();
        if ( collection != null ) {
            customerDto.setRoles( new ArrayList<Role>( collection ) );
        }
        customerDto.setCards( creditCardCollectionToCreditCardDtoCollection( customer.getCards() ) );

        return customerDto;
    }

    @Override
    public Customer customerDtoToCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setUsername( customerDto.getUsername() );
        customer.setEmail( customerDto.getEmail() );
        customer.setName( customerDto.getName() );
        customer.setSurname( customerDto.getSurname() );
        Collection<Role> collection = customerDto.getRoles();
        if ( collection != null ) {
            customer.setRoles( new ArrayList<Role>( collection ) );
        }
        customer.setCards( creditCardDtoCollectionToCreditCardCollection( customerDto.getCards() ) );

        return customer;
    }

    protected CreditCardDto creditCardToCreditCardDto(CreditCard creditCard) {
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

    protected Collection<CreditCardDto> creditCardCollectionToCreditCardDtoCollection(Collection<CreditCard> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<CreditCardDto> collection1 = new ArrayList<CreditCardDto>( collection.size() );
        for ( CreditCard creditCard : collection ) {
            collection1.add( creditCardToCreditCardDto( creditCard ) );
        }

        return collection1;
    }

    protected CreditCard creditCardDtoToCreditCard(CreditCardDto creditCardDto) {
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

    protected Collection<CreditCard> creditCardDtoCollectionToCreditCardCollection(Collection<CreditCardDto> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<CreditCard> collection1 = new ArrayList<CreditCard>( collection.size() );
        for ( CreditCardDto creditCardDto : collection ) {
            collection1.add( creditCardDtoToCreditCard( creditCardDto ) );
        }

        return collection1;
    }
}
