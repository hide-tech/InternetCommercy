package com.yazykov.shop.repositories;

import com.yazykov.shop.model.CurrentUser;
import com.yazykov.shop.model.Customer;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Long, Customer> {

    @Query("select new com.yazykov.shop.model.CurrentUser(c.id, c.username, c.password) " +
            "from customers c " +
            "where c.username = ?1")
    Mono<CurrentUser> findByUsername(String username);
}
