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
            postDto.setSoldQuantity(post.getSoldQuantity());
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
        postDto.setCreateAt(post.getCreatedAt());
        postDto.setCreateBy(post.getCreatedBy());
        postDto.setSoldQuantity(post.getSoldQuantity());
        Product product = productRepository.findById(post.getProduct().getId()).get();
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setProductPrice(product.getProductPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setProductThumbnail(product.getProductThumbnail());
        productDto.setDiscount(product.getDiscount());
        productDto.setAvgRating(null);
        postDto.setProductDto(productDto);
        return postDto;
    }
    public  List<PostDto> findPostsByUserId(long userId){
        List<Post> posts = postRepository.findAllByUserId(userId);
        List<PostDto> postDtos = new ArrayList<>();
        for(Post post : posts) {
            PostDto postDto = new PostDto();
            postDto.setId(1L);
            postDto.setPostTitle(post.getPostTitle());
            postDto.setSoldQuantity(post.getSoldQuantity());
            postDto.setPostDescription(post.getPostDescription());
            postDto.setCreateAt(post.getCreatedAt());
            postDto.setCreateBy(post.getCreatedBy());
            Product product = productRepository.findById(post.getProduct().getId()).get();
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setProductName(product.getProductName());
            productDto.setProductPrice(product.getProductPrice());
            productDto.setQuantity(product.getQuantity());
            productDto.setProductThumbnail(product.getProductThumbnail());
            productDto.setDiscount(product.getDiscount());
            productDto.setAvgRating(null);
            postDto.setProductDto(productDto);
            postDtos.add(postDto);
        }
        return postDtos;
    }
    public PostDto updatePost(PostDto postDto){
           Post post = new Post();
           ProductDto productDto = postDto.getProductDto();
           post.setId(postDto.getId());
           post.setPostTitle(postDto.getPostTitle());
           post.setPostDescription(postDto.getPostDescription());
           post.setCreatedBy(postDto.getCreateBy());
           post.setCreatedAt(postDto.getCreateAt());
           Post savePost = postRepository.save(post);
           Product product = productRepository.findById(savePost.getProduct().getId()).get();
           product.setProductPrice(productDto.getProductPrice());
           product.setProductName(productDto.getProductName());
           product.setDiscount(productDto.getDiscount());
           product.setProductThumbnail(productDto.getProductThumbnail());
           product.setQuantity(productDto.getQuantity());
           productRepository.save(product);
           return postDto;
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
    public boolean update(Post post){
       Post dbPost = postRepository.findById(post.getId()).get();
       if (dbPost == null ){
           return false;
       }
       post.setUser(dbPost.getUser());
       post.setCommentList(dbPost.getCommentList());
       postRepository.save(post);
       productRepository.save(post.getProduct());
       return true;
    }

    public void deleteAll(){
        postRepository.deleteAll();
    }

    public void delete(Long id){
        postRepository.deleteById(id);
    }
}
