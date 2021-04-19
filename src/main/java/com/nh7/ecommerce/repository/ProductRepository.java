package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Category;
import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.model.ProductCardModel;
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


    @Query(value = "select pr.* from product pr join category ca on pr.category_id = ca.id where ca.id = :id", nativeQuery = true)
    List<Product> findByCategoryId(@Param("id") long id);
}
