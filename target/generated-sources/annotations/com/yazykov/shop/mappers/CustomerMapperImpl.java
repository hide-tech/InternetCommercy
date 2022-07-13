package com.yazykov.shop.mappers;

import com.yazykov.shop.dto.CustomerDto;
import com.yazykov.shop.model.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-13T16:14:19+0300",
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
        customerDto.setRole( customer.getRole() );

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
        customer.setRole( customerDto.getRole() );

        return customer;
    }
}
