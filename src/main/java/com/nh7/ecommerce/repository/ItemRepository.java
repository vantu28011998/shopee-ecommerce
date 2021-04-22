package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    
}
