package com.nh7.ecommerce.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenueShopDto {
    private long shop_id;
    private String shopName;
    private String logo;
    private String ownerName;
    private int revenue = 0;
    private int quantitySell = 0;
}
