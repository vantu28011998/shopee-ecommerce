package com.nh7.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingInfoDto {
    private String fullName;
    private String phoneNumber;
    private String address;
    private String ward;
    private String district;
    private String city;
}
