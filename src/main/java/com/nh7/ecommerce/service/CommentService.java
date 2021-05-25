package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.Comment;
import com.nh7.ecommerce.repository.CommentRepository;
import com.nh7.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
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
        saveComment.setUser(userRepository.findById(userId).get());
        commentRepository.save(saveComment);
    }

}
