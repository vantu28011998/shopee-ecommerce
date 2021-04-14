package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findById(int id);
    @Query(value = "select * from product p join category c on\n" +
            "p.category_id = c.id where c.category_name like %:name%", nativeQuery = true)
    List<Product> findByCategoryName(@Param("name")String name);
    @Query(value = "")
    List<Product> findByCategoryId(@Param("id") long id);
}
