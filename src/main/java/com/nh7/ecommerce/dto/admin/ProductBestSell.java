package com.nh7.ecommerce.dto.admin;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProductBestSell {
    private LocalDateTime createdAt;
    private int discount;
    private String productName;
    private double productPrice;
    private String productThumbnail;
    private int soldQuantity;
    private String categoryName;
    private String subcategoryName;
}
