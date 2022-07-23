package com.yazykov.shop.repositories;

import com.yazykov.shop.model.Bucket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BucketRepository extends ReactiveMongoRepository<Bucket, String> {

    Mono<Bucket> findByCustomerId(Long customerId);
}
