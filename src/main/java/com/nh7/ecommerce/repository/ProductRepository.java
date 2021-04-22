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
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findById(long id);
    @Query(value = "SELECT pr.* \n" +
            "FROM product pr\n" +
            "JOIN SUB_CATEGORY sub_ca ON pr.subcategory_id=sub_ca.id\n" +
            "JOIN category ca ON ca.id=sub_ca.category_id\n" +
            "WHERE ca.id=:id", nativeQuery = true)
    List<Product> findByCategoryId(@Param("id") long id);
    @Modifying
    @Query(value = "DELETE FROM product WHERE product.id=:id",nativeQuery = true)
    @Transactional
    void deleteById(Long id);
    @Modifying
    @Query(value = "INSERT INTO product(avg_evalute,discount,product_name,product_price,product_thumbnail,quantity,subcategory_id) VALUES(:avg_evalute,:discount,:product_name,:product_price,:product_thumbnail,:quantity,:id)",nativeQuery = true)
    @Transactional
    void saveBySuper(@Param("id") long id,@Param("avg_evalute") Double avgEvalute,@Param("discount") int discount, @Param("product_name") String productName,@Param("product_price") Double productPrice,@Param("product_thumbnail") String productThumbnail,@Param("quantity") int quantity);
}
