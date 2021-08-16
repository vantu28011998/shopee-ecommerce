package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findById(long id);
    @Modifying
    @Query(value = "DELETE FROM post WHERE post.id=:id",nativeQuery = true)
    @Transactional
    void deleteById(long id);
    @Query(value = "select * from post po join product pr on pr.id = po.product_id where pr.id = :id ",nativeQuery = true)
    Post findByProductId(@Param("id")long id);
    @Query(value = "SELECT * FROM post WHERE user_id=:userId",nativeQuery = true)
    List<Post> findAllByUserId(@Param("userId") Long userId);
    @Query(value = "SELECT * FROM post WHERE user_id=:userId AND enable=true",nativeQuery = true)
    List<Post> findAllEnableProductsByUserId(@Param("userId") Long userId);
    @Query(value = "SELECT * FROM post WHERE user_id=:userId AND enable=false",nativeQuery = true)
    List<Post> findAllDisableProductsByUserId(@Param("userId") Long userId);
    @Modifying
    @Query(value = "UPDATE post SET enable = false WHERE post.id=:id",nativeQuery = true)
    @Transactional
    void disablePost(@Param("id") Long id);
}
