package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.PostDto;
import com.nh7.ecommerce.dto.ShopDto;
import com.nh7.ecommerce.entity.Post;
import com.nh7.ecommerce.entity.Shop;
import com.nh7.ecommerce.repository.PostRepository;
import com.nh7.ecommerce.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapperUtil modelMapperUtil;
    public List<PostDto> findAll(){
        List<Post> posts= (List<Post>) postRepository.findAll();
        return modelMapperUtil.mapList(posts,PostDto.class);
    }
    public PostDto findById(long id){
        Post post = postRepository.findById(id);
        return modelMapperUtil.map(post,PostDto.class);
    }

    public List<Post> saveAll(List<Post> posts){
        return (List<Post>) postRepository.saveAll(posts);
    }

    public Post save(Post post){
        return postRepository.save(post);
    }

    public void deleteAll(){
        postRepository.deleteAll();
    }

    public void delete(Long id){
        postRepository.deleteById(id);
    }
}
