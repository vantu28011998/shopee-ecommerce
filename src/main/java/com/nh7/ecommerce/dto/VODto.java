package com.nh7.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VODto {
    private long itemId;
    private String customerName;
    private String productName;
    private String postTitle;
    private int productQuantity;
    private String itemStatus;
    private LocalDateTime createdAt;
}
