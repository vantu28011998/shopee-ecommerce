package com.nh7.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String postTitle;
    private long product; // product id
    private String productName;
    private Double productPrice;
    private String productThumbnail;
    private int qty; // product quantity in item
    private int quantity;
}
