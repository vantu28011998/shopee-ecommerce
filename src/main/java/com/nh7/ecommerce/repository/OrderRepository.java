package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {
    UserOrder findById(long id);

    // (Admin) for get Recent Purchases in Week
    @Query(value = "select * from user_order where yearweek(created_at) = yearweek(now())", nativeQuery = true)
    List<UserOrder> getRecentPurchasesInWeek();

    // (Admin) for get Revenue in Month Of Year
    @Query(value = "select sum(order_price) from user_order where month(created_at) = :month and year(created_at) = :year", nativeQuery = true)
    Long getRevenueInMonth(@Param("month") int month, @Param("year") int year);

    // (Admin) for get count Order in Month of  Year
    @Query(value = "select count(id) from user_order where month(created_at) = :month and year(created_at) = :year", nativeQuery = true)
    Integer getCountOrdersInMonth(@Param("month") int month, @Param("year") int year);

    // (Admin) for get Total Revenue
    @Query(value = "select sum(uo.order_price) from user_order uo\n" +
            "join item on item.order_id = uo.id\n" +
            "join product pr on pr.id = item.product_id\n" +
            "join post po on po.product_id = pr.id\n" +
            "join u on po.user_id = u.id \n" +
            "join shop sh on sh.user_id = u.id\n" +
            "where sh.id = :id", nativeQuery = true)
    Long getTotalRevenue(@Param("id") long id);
}
