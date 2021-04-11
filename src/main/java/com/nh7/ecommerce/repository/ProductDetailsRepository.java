package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.ProductDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends CrudRepository<ProductDetails, Integer> {
    ProductDetails findById(int id);
}
