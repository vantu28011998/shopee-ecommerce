package com.nh7.ecommerce.dto;

import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;

@Getter
@Setter
public class PostDto {
    private Long id;
    private String postTitle;
    private String postDescription;
    private Product product;
    private User user;
}
