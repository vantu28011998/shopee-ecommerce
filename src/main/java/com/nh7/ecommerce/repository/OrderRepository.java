package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.UserOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<UserOrder, Long> {
    UserOrder findById(int id);
}
