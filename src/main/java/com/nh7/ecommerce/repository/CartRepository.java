package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
    Cart findById(int id);
}
