package com.yazykov.shop.repositories;

import com.yazykov.shop.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<String, Product> {

}