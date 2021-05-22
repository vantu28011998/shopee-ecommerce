package com.nh7.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDto {
    private long id;
    private String vendorName;
    private String vendorAvatar;
    private String shopName;
    private int products = new Integer(0); // count product of Vendor
    private LocalDateTime createdAt;
    private Long Revenue = new Long(0);
}
