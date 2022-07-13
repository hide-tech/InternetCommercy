package com.yazykov.shop.mappers;

import com.yazykov.shop.dto.AddressDto;
import com.yazykov.shop.dto.BucketDto;
import com.yazykov.shop.dto.ProductDto;
import com.yazykov.shop.model.Address;
import com.yazykov.shop.model.Bucket;
import com.yazykov.shop.model.Product;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-13T16:14:19+0300",
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

        bucket.setDeliveryAddress( addressDtoToAddress( bucketDto.getDeliveryAddress() ) );
        bucket.setProducts( productDtoCollectionToProductCollection( bucketDto.getProducts() ) );
        bucket.setPaid( bucketDto.getPaid() );

        return bucket;
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

        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setModel( product.getModel() );
        productDto.setDescription( product.getDescription() );
        productDto.setPrice( product.getPrice() );
        productDto.setQuantity( product.getQuantity() );

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

        product.setId( productDto.getId() );
        product.setName( productDto.getName() );
        product.setModel( productDto.getModel() );
        product.setDescription( productDto.getDescription() );
        product.setPrice( productDto.getPrice() );
        product.setQuantity( productDto.getQuantity() );

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
