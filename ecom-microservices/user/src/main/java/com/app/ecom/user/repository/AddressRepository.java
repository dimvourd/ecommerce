package com.app.ecom.user.repository;

import com.app.ecom.user.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
