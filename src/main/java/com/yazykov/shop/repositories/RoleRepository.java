package com.yazykov.shop.repositories;

import com.yazykov.shop.model.Roles;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RoleRepository extends R2dbcRepository<Roles, Long> {

    @Query("select r.name from customers c left join user_roles ur on c.id=ur.customer_id " +
            "left join roles r on ur.role_id=r.id where c.username=?1")
    Flux<String> findRolesByUsername(String username);

}
