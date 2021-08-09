package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Shop findById(long id);
    @Query(value = "select * from shop where user_id= :id AND enable=true", nativeQuery=true)
    Shop findByUserId(@Param("id") long id);
    @Query(value = "select count(shop.id) from shop", nativeQuery=true)
    int countById();

    @Modifying
    @Query(value = "DELETE FROM shop WHERE shop.id=:id",nativeQuery = true)
    @Transactional
    void deleteById(long id);

    @Query(value = "select shop.*  from shop join item\n" +
            "on shop.id = item.shop_id \n" +
            "where item_status = 'COMPLETED' and date_part('month',item.created_at) = :currentMonth and date_part('year',item.created_at) = :currentYear\n" +
            "group by shop.id\n" +
            "order by sum(item_price) desc\n" +
            "limit :limit", nativeQuery = true)
    List<Shop> revenueShop(@Param("currentMonth") int currentMonth, @Param("currentYear") int currentYear, @Param("limit") int limit);

    @Query(value = "select sum(i.item_price) from shop sh\n" +
            "join item i on sh.id = i.shop_id", nativeQuery = true)
    String sumAllRevenue();
}
