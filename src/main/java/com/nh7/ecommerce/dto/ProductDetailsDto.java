package com.nh7.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDetailsDto {
    // information of Product
    private long id;
    private String productName;
    private Double productPrice;
    private String productThumbnail;
    private int quantity;
    private int discount;
    private double avgEvalute;
    // information of shop
    private String shopLogo;
    private String shopName;
    private String shopAddress;
    private String shopPhone;
    // sum of Product quantity
    private int sumProduct;
    // category and sub category
    private String categoryName;
    private String subCategoryName;
    // information of Post
    private String postTitle;
    private String postDecription;
    private int soldQuantity;
    // information of Comment
    List<CommentCardDto> commentList;
    private int sumComment;
}
