package com.nh7.ecommerce.model;

import com.nh7.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import javax.persistence.Entity;

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
