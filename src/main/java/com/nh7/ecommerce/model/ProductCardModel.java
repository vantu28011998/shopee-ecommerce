package com.nh7.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCardModel {
    //product
    private Long id;
    private String thumbnail;
    private Double productPrice;
    //post
    private String postTitle;
    //shop
    private String address;
    //order
    private int soldQuantity;
}
