package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findById(long id);

    @Query(value = "select * from item where shop_id = :id order by created_at desc limit :limit offset :offset", nativeQuery = true)
    List<Item> getAllByShopId(@Param("id") long id, @Param("limit") int limit, @Param("offset") int offset);
}
