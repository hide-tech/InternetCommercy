package com.yazykov.shop.mappers;

import com.yazykov.shop.dto.AddressDto;
import com.yazykov.shop.model.Address;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-28T11:04:47+0300",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDto addressToAddressDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDto addressDto = new AddressDto();

        addressDto.setZip( address.getZip() );
        addressDto.setCountry( address.getCountry() );
        addressDto.setCity( address.getCity() );
        addressDto.setStreet( address.getStreet() );
        addressDto.setBuilding( address.getBuilding() );
        addressDto.setExtension( address.getExtension() );
        addressDto.setFlat( address.getFlat() );

        return addressDto;
    }

    @Override
    public Address addressDtoToAddress(AddressDto addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        Address address = new Address();

        address.setZip( addressDto.getZip() );
        address.setCountry( addressDto.getCountry() );
        address.setCity( addressDto.getCity() );
        address.setStreet( addressDto.getStreet() );
        address.setBuilding( addressDto.getBuilding() );
        address.setExtension( addressDto.getExtension() );
        address.setFlat( addressDto.getFlat() );

        return address;
    }
}
