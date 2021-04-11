package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    Order findById(int id);
}
