package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Comment findById(int id);
}
