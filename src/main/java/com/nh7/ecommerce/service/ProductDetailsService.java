package com.nh7.ecommerce.service;


import com.nh7.ecommerce.dto.CommentCardDto;
import com.nh7.ecommerce.dto.ProductDetailsDto;
import com.nh7.ecommerce.entity.Comment;
import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.repository.ProductRepository;
import com.nh7.ecommerce.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDetailsService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RatingRepository ratingRepository;
    public ProductDetailsDto getProductDetailByProductId(long id){
        ProductDetailsDto productDetailsDTO = new ProductDetailsDto();
        Product product = productRepository.findById(id);
        productDetailsDTO.setId(product.getId());
        productDetailsDTO.setProductName(product.getProductName());
        productDetailsDTO.setProductPrice(product.getProductPrice());
        productDetailsDTO.setProductThumbnail(product.getProductThumbnail());
        productDetailsDTO.setQuantity(product.getQuantity());
        productDetailsDTO.setDiscount(product.getDiscount());
        productDetailsDTO.setAvgRating(ratingRepository.findAvgRating(product.getId()));
        productDetailsDTO.setCategoryName(product.getSubCategory().getCategory().getCategoryName());
        productDetailsDTO.setSubCategoryName(product.getSubCategory().getSubCategoryName());
        productDetailsDTO.setShopId(product.getPost().getUser().getShop().getId());
        productDetailsDTO.setShopLogo(product.getPost().getUser().getShop().getLogo());
        productDetailsDTO.setShopName(product.getPost().getUser().getShop().getName());
        productDetailsDTO.setShopAddress(product.getPost().getUser().getShop().getAddress());
        productDetailsDTO.setShopPhone(product.getPost().getUser().getShop().getPhoneNumber());
        productDetailsDTO.setSumProduct(productRepository.countProductByPostUserShop(product.getPost().getUser().getShop().getId()));
        productDetailsDTO.setPostTitle(product.getPost().getPostTitle());
        productDetailsDTO.setPostDescription(product.getPost().getPostDescription());
        productDetailsDTO.setSoldQuantity(product.getPost().getSoldQuantity());
        List<CommentCardDto> commentCardDtos = new ArrayList<>();
        List<Comment> comments = product.getPost().getCommentList();
        for (Comment comment : comments){
            CommentCardDto commentCardDTO = new CommentCardDto();
            commentCardDTO.setContent(comment.getContent());
            commentCardDTO.setRating(comment.getRating());
                commentCardDTO.setUserName(comment.getUser().getUsername());
            commentCardDTO.setUserAvatar(comment.getUser().getAvatar());
            commentCardDtos.add(commentCardDTO);
        }
        productDetailsDTO.setCommentList(commentCardDtos);
        productDetailsDTO.setSumComment(product.getPost().getCommentList().size());
        return productDetailsDTO;
    }
}
