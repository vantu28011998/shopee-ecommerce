package com.nh7.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private int quantity;
    private String productName;
    private Double avgRating;
    private String productThumbnail;
    private Double productPrice;
    private int discount;
}
