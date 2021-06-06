package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.PostDto;
import com.nh7.ecommerce.dto.ProductDto;
import com.nh7.ecommerce.dto.ShopDto;
import com.nh7.ecommerce.entity.Post;
import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.entity.Shop;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.repository.*;
import com.nh7.ecommerce.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RatingRepository ratingRepository;
    Post getPostById(int id) {return postRepository.findById(id);}
    List<Post> getAllPost(){return (List<Post>) postRepository.findAll();}
    // CODE BY HUY
    void create(Post post){postRepository.save(post);}
    //
    @Autowired
    private ModelMapperUtil modelMapperUtil;
    public List<PostDto> findAll(){
        List<Post> posts= (List<Post>) postRepository.findAll();
        List<PostDto> postDtos = new ArrayList<>();
        for(Post post : posts){
            PostDto postDto = new PostDto();
            postDto.setId(post.getId());
            postDto.setPostTitle(post.getPostTitle());
            postDto.setPostDescription(post.getPostDescription());
            ProductDto productDto = new ProductDto();
            Product product = post.getProduct();
            productDto.setId(product.getId());
            productDto.setQuantity(product.getQuantity());
            productDto.setAvgRating(ratingRepository.findAvgRating(product.getId()));
            productDto.setProductName(product.getProductName());
            productDto.setProductThumbnail(product.getProductThumbnail());
            productDto.setProductPrice(product.getProductPrice());
            productDto.setDiscount(product.getDiscount());
            postDto.setProductDto(productDto);
            postDto.setCreateBy(post.getCreatedBy());
            postDto.setCreateAt(post.getCreatedAt());
            postDtos.add(postDto);
        }
        return postDtos;
    }
    public PostDto findById(long id){
        Post post = postRepository.findById(id);
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setPostTitle(post.getPostTitle());
        postDto.setPostDescription(post.getPostDescription());
        return postDto;
    }
    public List<ProductDto> findProductInPost(Long id){
        List<Post> posts = postRepository.findAllByUserId(id);
        List<ProductDto> productDtos = new ArrayList<>();
        for(Post post : posts){
            Product product = post.getProduct();
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setProductName(product.getProductName());
            productDto.setProductPrice(product.getProductPrice());
            productDto.setProductThumbnail(product.getProductThumbnail());
            productDto.setDiscount(product.getDiscount());
            productDto.setQuantity(product.getQuantity());
            productDto.setAvgRating(ratingRepository.findAvgRating(product.getId()));
            productDtos.add(productDto);
        }
        return productDtos;
    }
    public List<Post> saveAll(List<Post> posts){
        return (List<Post>) postRepository.saveAll(posts);
    }

    public void save(Post post){
        Post savePost = postRepository.save(post);
        Long id = userRepository.findIdByUsername(post.getCreatedBy());
        User user = userRepository.findById(id).get();
        savePost.setUser(user);
        postRepository.save(savePost);
    }

    public void deleteAll(){
        postRepository.deleteAll();
    }

    public void delete(Long id){
        postRepository.deleteById(id);
    }
}
