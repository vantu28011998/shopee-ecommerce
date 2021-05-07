package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.*;
import com.nh7.ecommerce.model.ProductCardModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findById(long id);
    @Query(value = "select pr.* from product pr \n" +
            "join sub_category su on pr.subcategory_id = su.id\n" +
            "join category ca on ca.id = su.category_id\n" +
            "where ca.id = :id", nativeQuery = true)
    List<Product> findByCategoryId(@Param("id") long id);
    @Modifying
    @Query(value = "DELETE FROM product WHERE product.id=:id",nativeQuery = true)
    @Transactional
    void deleteById(Long id);

    @Query(value = "select count(pr.id) from product pr\n" +
            "join post po on pr.id = po.product_id\n" +
            "join u on u.id = po.user_id\n" +
            "join shop sh on sh.user_id = u.id\n" +
            "where sh.id = :id", nativeQuery = true)
    Integer countProductByPostUserShop(@Param("id") long id);
}
