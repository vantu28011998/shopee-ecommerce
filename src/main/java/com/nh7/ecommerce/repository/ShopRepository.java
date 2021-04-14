package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Integer> {
    Shop findById(int id);

}
