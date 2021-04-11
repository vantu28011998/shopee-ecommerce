package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails, Integer> {
    UserDetails findById(int id);
}
