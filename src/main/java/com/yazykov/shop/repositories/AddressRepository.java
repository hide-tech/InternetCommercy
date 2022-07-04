package com.yazykov.shop.repositories;

import com.yazykov.shop.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Long, Address> {

}
