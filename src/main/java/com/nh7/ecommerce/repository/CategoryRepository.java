package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
    Category findById(int id);
    @Query(value = "select * from category c where c.category_name like %:name%", nativeQuery = true)
    List<Category> findByCategoryName(@Param("name") String name);
}
