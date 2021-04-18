package com.nh7.ecommerce.model;

import com.nh7.ecommerce.repository.ProductRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import javax.persistence.Entity;

@Getter
@Setter
public class ProductCardModel {
    @Autowired
    private ProductRepository productRepository;

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

    public ProductCardModel(Long id, String productThumbnail, Double productPrice, String postTitle, String address, int soldQuantity) {
        this.id = id;
        this.productThumbnail = productThumbnail;
        this.productPrice = productPrice;
        this.postTitle = postTitle;
        this.address = address;
        this.soldQuantity = soldQuantity;
    }
}
