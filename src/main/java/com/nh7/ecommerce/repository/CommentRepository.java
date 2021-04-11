package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Comment findById(int id);
}
