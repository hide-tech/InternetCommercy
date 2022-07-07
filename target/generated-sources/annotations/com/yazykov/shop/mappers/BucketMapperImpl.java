package com.yazykov.shop.mappers;

import com.yazykov.shop.dto.AddressDto;
import com.yazykov.shop.dto.BucketDto;
import com.yazykov.shop.dto.CreditCardDto;
import com.yazykov.shop.dto.CustomerDto;
import com.yazykov.shop.dto.ProductDto;
import com.yazykov.shop.model.Address;
import com.yazykov.shop.model.Bucket;
import com.yazykov.shop.model.CreditCard;
import com.yazykov.shop.model.Customer;
import com.yazykov.shop.model.Product;
import com.yazykov.shop.model.Role;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-07T15:37:44+0300",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class BucketMapperImpl implements BucketMapper {

    @Override
    public BucketDto bucketToBucketDto(Bucket bucket) {
        if ( bucket == null ) {
            return null;
        }

        BucketDto bucketDto = new BucketDto();

        bucketDto.setCustomer( customerToCustomerDto( bucket.getCustomer() ) );
        bucketDto.setDeliveryAddress( addressToAddressDto( bucket.getDeliveryAddress() ) );
        bucketDto.setProducts( productCollectionToProductDtoCollection( bucket.getProducts() ) );
        bucketDto.setPaid( bucket.getPaid() );

        return bucketDto;
    }

    @Override
    public Bucket bucketDtoToBucket(BucketDto bucketDto) {
        if ( bucketDto == null ) {
            return null;
        }

        Bucket bucket = new Bucket();

        bucket.setCustomer( customerDtoToCustomer( bucketDto.getCustomer() ) );
        bucket.setDeliveryAddress( addressDtoToAddress( bucketDto.getDeliveryAddress() ) );
        bucket.setProducts( productDtoCollectionToProductCollection( bucketDto.getProducts() ) );
        bucket.setPaid( bucketDto.getPaid() );

        return bucket;
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

    protected CustomerDto customerToCustomerDto(Customer customer) {
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

    protected AddressDto addressToAddressDto(Address address) {
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

    protected ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setName( product.getName() );
        productDto.setModel( product.getModel() );
        productDto.setDescription( product.getDescription() );
        productDto.setPrice( product.getPrice() );
        productDto.setQuantity( product.getQuantity() );
        productDto.setSupply_date( product.getSupply_date() );

        return productDto;
    }

    protected Collection<ProductDto> productCollectionToProductDtoCollection(Collection<Product> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<ProductDto> collection1 = new ArrayList<ProductDto>( collection.size() );
        for ( Product product : collection ) {
            collection1.add( productToProductDto( product ) );
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

    protected Customer customerDtoToCustomer(CustomerDto customerDto) {
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

    protected Address addressDtoToAddress(AddressDto addressDto) {
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

    protected Product productDtoToProduct(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( productDto.getName() );
        product.setModel( productDto.getModel() );
        product.setDescription( productDto.getDescription() );
        product.setPrice( productDto.getPrice() );
        product.setQuantity( productDto.getQuantity() );
        product.setSupply_date( productDto.getSupply_date() );

        return product;
    }

    protected Collection<Product> productDtoCollectionToProductCollection(Collection<ProductDto> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<Product> collection1 = new ArrayList<Product>( collection.size() );
        for ( ProductDto productDto : collection ) {
            collection1.add( productDtoToProduct( productDto ) );
        }

        return collection1;
    }
}
