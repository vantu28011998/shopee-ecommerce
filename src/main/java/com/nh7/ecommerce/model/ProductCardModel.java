package com.nh7.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCardModel {

    //product
    private Long id;
    private String productThumbnail;
    private Double productPrice;
    //post
    private String postTitle;
    //shop
    private String address;
    //order
    private int soldQuantity;

}
