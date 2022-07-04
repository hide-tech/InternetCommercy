package com.yazykov.shop.repositories;

import com.yazykov.shop.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Long, Customer> {

}
