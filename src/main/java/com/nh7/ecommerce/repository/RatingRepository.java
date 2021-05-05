package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends CrudRepository<Rating,Long> {
}
