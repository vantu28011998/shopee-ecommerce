package com.nh7.ecommerce.dto;

import com.nh7.ecommerce.entity.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;

@Getter
@Setter
public class PostDto {
    private Long id;
    private String postTitle;
    private String postDecription;
    private int soldQuantity;
    private Product product;
    private Long userId;
}
