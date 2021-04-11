package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    Post findById(int id);
}
