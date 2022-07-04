package com.yazykov.shop.mappers;

import com.yazykov.shop.dto.BucketDto;
import com.yazykov.shop.model.Bucket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BucketMapper {

    BucketDto bucketToBucketDto(Bucket bucket);

    Bucket bucketDtoToBucket(BucketDto bucketDto);

}
