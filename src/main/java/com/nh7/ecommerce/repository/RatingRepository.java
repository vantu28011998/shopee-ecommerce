package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    @Query(value = "select avg(rating.rating) from rating where product_id=:productId",nativeQuery = true)
    Double findAvgRating(@Param("productId") Long productId);
}
