package com.nh7.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemUDto {
    private String postTitle;
    private long product; // product id
    private String productName;
    private Double itemPrice;
    private String productThumbnail;
    private int qty; // product quantity in item
    private String itemStatus;
}
