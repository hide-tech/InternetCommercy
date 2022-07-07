package com.yazykov.shop.repositories;

import com.yazykov.shop.model.Roles;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends R2dbcRepository<Roles, Long> {

}
