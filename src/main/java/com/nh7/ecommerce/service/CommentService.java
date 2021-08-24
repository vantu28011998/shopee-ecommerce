package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.Comment;
import com.nh7.ecommerce.entity.Post;
import com.nh7.ecommerce.entity.Rating;
import com.nh7.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ProductRepository productRepository;
    public boolean saveAll(List<Comment> commentList){
        try {
            commentRepository.saveAll(commentList);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public void save(Comment comment){
        Comment saveComment = commentRepository.save(comment);
        Long userId = userRepository.findIdByUsername(saveComment.getCreatedBy());
        if (userId == null) return;
        saveComment.setUser(userRepository.findById(userId).get());
        Rating rating = new Rating();
        rating.setRating(saveComment.getRating());
        rating.setUser(saveComment.getUser());
        Post post =postRepository.findById(saveComment.getPost().getId()).get();
        rating.setProduct(productRepository.findById(post.getProduct().getId()).get());
        ratingRepository.save(rating);
        commentRepository.save(saveComment);
    }

}
