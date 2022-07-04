package com.yazykov.shop.mappers;

import com.yazykov.shop.dto.AddressDto;
import com.yazykov.shop.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto addressToAddressDto(Address address);

    Address addressDtoToAddress(AddressDto addressDto);

}
