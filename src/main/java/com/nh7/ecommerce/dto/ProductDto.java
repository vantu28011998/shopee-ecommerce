package com.nh7.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private int quantity;
    private Double avgEvalute;
    private String productThumbnail;
    private Double productPrice;
    private int discount;
}
