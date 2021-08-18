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
            postDto.setId(post.getId());
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
            if(post.getEnable()==false){
                postDto.setStatus(-1);
            }else if(product.getQuantity()<1){
                postDto.setStatus(0);
            }else{
                postDto.setStatus(1);
            }
            postDtos.add(postDto);
        }
        return postDtos;
    }
    public  List<PostDto> findPostsActiveProductsByUserId(long userId){
        List<Post> posts = postRepository.findAllEnableProductsByUserId(userId);
        List<PostDto> postDtos = new ArrayList<>();
        for(Post post : posts) {
            Product product = productRepository.findById(post.getProduct().getId()).get();
            if (product.getQuantity()>0){
                PostDto postDto = new PostDto();
                postDto.setId(post.getId());
                postDto.setPostTitle(post.getPostTitle());
                postDto.setSoldQuantity(post.getSoldQuantity());
                postDto.setPostDescription(post.getPostDescription());
                postDto.setCreateAt(post.getCreatedAt());
                postDto.setCreateBy(post.getCreatedBy());

                ProductDto productDto = new ProductDto();
                productDto.setId(product.getId());
                productDto.setProductName(product.getProductName());
                productDto.setProductPrice(product.getProductPrice());
                productDto.setQuantity(product.getQuantity());
                productDto.setProductThumbnail(product.getProductThumbnail());
                productDto.setDiscount(product.getDiscount());
                productDto.setAvgRating(null);
                postDto.setProductDto(productDto);
                postDto.setStatus(1);
                postDtos.add(postDto);
            }
        }
        return postDtos;
    }
    public  List<PostDto> findPostsDisableProductsByUserId(long userId){
        List<Post> posts = postRepository.findAllDisableProductsByUserId(userId);
        List<PostDto> postDtos = new ArrayList<>();
        for(Post post : posts) {
            PostDto postDto = new PostDto();
            postDto.setId(post.getId());
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
            postDto.setStatus(-1);
            postDtos.add(postDto);
        }
        return postDtos;
    }
    public  List<PostDto> findPostEmptyProductsByUserId(long userId){
        List<Post> posts = postRepository.findAllByUserId(userId);
        List<PostDto> postDtos = new ArrayList<>();
        for(Post post : posts) {
            Product product = productRepository.findById(post.getProduct().getId()).get();
            if (product.getQuantity()==0){
                PostDto postDto = new PostDto();
                postDto.setId(post.getId());
                postDto.setPostTitle(post.getPostTitle());
                postDto.setSoldQuantity(post.getSoldQuantity());
                postDto.setPostDescription(post.getPostDescription());
                postDto.setCreateAt(post.getCreatedAt());
                postDto.setCreateBy(post.getCreatedBy());

                ProductDto productDto = new ProductDto();
                productDto.setId(product.getId());
                productDto.setProductName(product.getProductName());
                productDto.setProductPrice(product.getProductPrice());
                productDto.setQuantity(product.getQuantity());
                productDto.setProductThumbnail(product.getProductThumbnail());
                productDto.setDiscount(product.getDiscount());
                productDto.setAvgRating(null);
                postDto.setProductDto(productDto);
                postDto.setStatus(0);
                postDtos.add(postDto);
            }

        }
        return postDtos;
    }




    public PostDto updatePost(PostDto postDto){
             Post dbPost = postRepository.findById(postDto.getId()).get();
             if (dbPost == null) return null;
             if (!postDto.getPostTitle().equals(dbPost.getPostTitle())){
                 dbPost.setPostTitle(postDto.getPostTitle());
             }
             if (!postDto.getPostDescription().equals(dbPost.getPostDescription())){
                 dbPost.setPostDescription(postDto.getPostDescription());
             }
             Product dbProduct = productRepository.findById(dbPost.getProduct().getId()).get();
            ProductDto productDto = postDto.getProductDto();
             if (!dbProduct.getProductPrice().equals(productDto.getProductPrice())){
                 dbProduct.setProductPrice(productDto.getProductPrice());
             }
             if (!dbProduct.getProductName().equals(productDto.getProductName())){
                 dbProduct.setProductName(productDto.getProductName());
             }
             if (dbProduct.getDiscount() != productDto.getDiscount()){
                 dbProduct.setDiscount(productDto.getDiscount());
             }
             if (dbProduct.getProductThumbnail().equals(productDto.getProductThumbnail())){
                 dbProduct.setProductThumbnail(productDto.getProductThumbnail());
             }
             if(dbProduct.getQuantity() != productDto.getQuantity()){
                 dbProduct.setQuantity(productDto.getQuantity());
             }
             postRepository.save(dbPost);
             productRepository.save(dbProduct);
             postDto.getProductDto().setId(dbProduct.getId());
             postDto.setCreateBy(dbPost.getCreatedBy());
             postDto.setCreateAt(dbPost.getCreatedAt());
             postDto.setSoldQuantity(dbPost.getSoldQuantity());
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
        savePost.setEnable(true);
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
        postRepository.disablePost(id);
    }
}
