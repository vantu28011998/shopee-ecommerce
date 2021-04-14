package com.nh7.ecommerce.repository;

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

    @Query(value = "select distinct pr.id,pr.product_thumbnail,pr.product_price,po.post_title,sh.address,SUM(it.item_quantity) as sold_quantity from product pr\n" +
            "join post po on pr.id = po.product_id\n" +
            "join user us on po.user_id = us.id\n" +
            "join user_order uo on uo.user_id = us.id\n" +
            "join shop sh on sh.user_id = us.id\n" +
            "join item it on it.product_id = pr.id\n" +
            "where pr.id = :id\n" +
            "group by uo.id", nativeQuery = true)
    List<ProductCardModel> findProductCardByCategoryId(@Param("id") long id);
}
