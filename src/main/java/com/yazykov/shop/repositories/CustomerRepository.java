package com.yazykov.shop.repositories;

import com.yazykov.shop.model.Customer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends R2dbcRepository<Customer, Long> {
}
