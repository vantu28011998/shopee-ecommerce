package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.Comment;
import com.nh7.ecommerce.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
}
