package com.yazykov.shop.mappers;

import com.yazykov.shop.dto.CustomerDto;
import com.yazykov.shop.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customerDto);

}
