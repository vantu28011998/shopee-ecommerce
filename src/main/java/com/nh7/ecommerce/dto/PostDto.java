package com.nh7.ecommerce.dto;

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
    private Long productId;
    private Long userId;
}
