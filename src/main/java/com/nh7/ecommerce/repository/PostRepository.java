package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    Post findById(long id);
    @Modifying
    @Query(value = "DELETE FROM post WHERE post.id=:id",nativeQuery = true)
    @Transactional
    void deleteById(long id);
    @Query(value = "select * from post po join product pr on pr.id = po.product_id where pr.id = :id ",nativeQuery = true)
    Post findByProductId(@Param("id")long id);
}
