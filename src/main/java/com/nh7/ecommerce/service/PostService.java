package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.Post;
import com.nh7.ecommerce.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    Post getPostById(int id) {return postRepository.findById(id);}
    List<Post> getAllPost(){return (List<Post>) postRepository.findAll();}
}
